/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.atom.core.ToStringStyle;

/**
 * Web请求对象工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: RequestUtils.java, 2012-3-18 下午5:15:21 Exp $
 */
public abstract class RequestUtils {

    /**
     * 获取访问者IP。
     * <p/>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * </p>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request.getRemoteAddr()。
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");

        if (!StringUtils.isBlank(ip) && !StringUtils.equalsIgnoreCase("unknown", ip)) {
            return ip;
        }

        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !StringUtils.equalsIgnoreCase("unknown", ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            ip = StringUtils.substringBefore(ip, ",");
        } else {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
    
    /**
     * 获取所有请求参数
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getParameters(ServletRequest request) {
        Map<String, Object> params = new ConcurrentHashMap<String, Object>();
        
        Map<Object, Object> srcObjs = request.getParameterMap();
        for(Map.Entry<Object, Object> srcObj : srcObjs.entrySet()) {
            params.put(ObjectUtils.toString(srcObj.getKey()), srcObj.getValue());
        }
        
        return params;
    }
    
    /**
     * 打印出所有的请求参数
     */
    public static void dumpRequest(ServletRequest request) {
        StringBuilder txt = new StringBuilder();
        
        Map<String, Object> params = RequestUtils.getParameters(request);
        for(Map.Entry<String, Object> param : params.entrySet()) {
            txt.append("\t");
            txt.append(param.getKey());
            txt.append("=");
            txt.append(ToStringBuilder.reflectionToString(param.getValue(), ToStringStyle.STYLE));
            txt.append(SystemUtils.LINE_SEPARATOR);
        }
        
        System.out.println(txt.toString());
    }

}
