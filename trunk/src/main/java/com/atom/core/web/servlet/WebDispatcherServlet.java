/**
 * aptech.com Inc.
 * Copyright (c) 2008-2012 All Rights Reserved.
 */
package com.atom.core.web.servlet;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.atom.core.LogConsts;
import com.atom.core.Profiler;

/**
 * Web请求分发控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebDispatcherServlet.java, 2012-1-11 下午12:53:50 Exp $
 */
public final class WebDispatcherServlet extends DispatcherServlet {
    private static final long   serialVersionUID = -7956676216338043105L;

    /** Logger */
    private static final Logger logger           = Logger.getLogger(LogConsts.WEB);

    /** 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // 初始化上下文
        WebContext.init(config);
    }

    /** 
     * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 计时
        Profiler.enter("Web请求，URL:" + this.getQueryURL(request));

        // 打印请求信息（DEBUG）
        this.dumpRequest(request);

        try {
            // 设置上下文
            WebContext.set(new WebContext(request, response));

            // 执行业务逻辑
            super.doDispatch(request, response);
        } finally {
            // 清理上下文
            WebContext.remove();

            // 清理计时
            Profiler.release();
        }
    }

    /** 
     * @see org.springframework.web.servlet.FrameworkServlet#initFrameworkServlet()
     */
    protected void initFrameworkServlet() throws ServletException {
        super.initFrameworkServlet();

        ApplicationContext context = this.getWebApplicationContext();
        this.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
    }

    /**
     * 取得URI+QueryString字符串
     */
    private String getQueryURL(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String query = request.getQueryString();

        if (StringUtils.isBlank(query)) {
            uri += ("?" + query);
        }

        return uri;
    }

    /**
     * 打印请求信息（DEBUG）
     */
    private void dumpRequest(HttpServletRequest request) {
        if (logger.isInfoEnabled()) {
            StringBuilder txt = new StringBuilder(256);

            // request中参数
            txt.append("Web请求参数：").append(SystemUtils.LINE_SEPARATOR);
            @SuppressWarnings("unchecked")
            Map<Object, Object> params = request.getParameterMap();
            for (Map.Entry<Object, Object> entry : params.entrySet()) {
                txt.append("\t").append(entry.getKey()).append("=").append(entry.getValue()).append(SystemUtils.LINE_SEPARATOR);
            }

            // cookie中的参数
            txt.append("Cookie请求参数：").append(SystemUtils.LINE_SEPARATOR);
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                txt.append("\t").append(this.toString(cookie)).append(SystemUtils.LINE_SEPARATOR);
            }

            // 打印
            logger.info(txt.toString());
        }
    }

    /**
     * Cookie转化为字符串
     */
    private String toString(Cookie cookie) {
        return ToStringBuilder.reflectionToString(cookie, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
