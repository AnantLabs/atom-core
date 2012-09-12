/**
 * aptech.com Inc.
 * Copyright (c) 2008-2012 All Rights Reserved.
 */
package com.atom.core.web.servlet.filter;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.atom.core.LogConsts;
import com.atom.core.Profiler;

/**
 * 记录Servlet处理时间的Filter
 * 
 * <p>
 * <code>web.xml</code>配置文件格式如下：
 * <pre><![CDATA[
 <filter>
 <filter-name>timer</filter-name>
 <filter-class>com.aptech.atom.web.servlet.filter.TimerFilter</filter-class>
 <init-param>
 <param-name>threshold</param-name>
 <param-value>30000</param-value>
 </init-param>
 </filter>
 ]]></pre>
 * </p>
 * 
 * <p>
 * 其中<code>threshold</code>参数表明超时阈值，如果servlet处理的总时间超过该值，则filter会以warning的方式记录该次操作。
 * </p>
 *
 * <p>
 *  增加了记录页面digest的日志，用于监控。 
 * </p>
 * 
 * @author obullxl@gmail.com
 * @version $Id: TimerFilter.java, 2012-2-28 下午12:48:07 Exp $
 */
public class TimerFilter extends AbstractFilter {
    /** Logger */
    private static final Logger pagelogger = Logger.getLogger(LogConsts.DIGEST);

    /**
     * 参数：时间值
     */
    private int                 threshold;

    /**
     * 初始化filter, 设置监视参数.
     *
     * @throws ServletException 初始化失败
     */
    public void init() throws ServletException {
        String thresholdString = findInitParameter("threshold", "30000");

        if (thresholdString != null) {
            try {
                threshold = Integer.parseInt(thresholdString);
            } catch (NumberFormatException e) {
                threshold = 0;
            }

            if (threshold <= 0) {
                throw new ServletException(MessageFormat.format("Invalid init parameter for filter: threshold = {0}", new Object[] { thresholdString }));
            }
        }

        logger.info(MessageFormat.format("Timer filter started with threshold {0,number}ms", new Object[] { new Integer(threshold) }));
    }

    /**
     * 执行filter.
     *
     * @param request HTTP请求
     * @param response HTTP响应
     * @param chain filter链
     *
     * @throws IOException 处理filter链时发生输入输出错误
     * @throws ServletException 处理filter链时发生的一般错误
     */
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 开始处理request, 并计时.
        String requestString = dumpRequest(request);

        if (logger.isInfoEnabled()) {
            logger.info("Started processing request: " + requestString);
        }

        Profiler.start("Process HTTP request");

        Throwable failed = null;

        try {
            chain.doFilter(request, response);
        } catch (Throwable e) {
            failed = e;
        } finally {
            Profiler.release();

            long duration = Profiler.getDuration();
            String detail = Profiler.dump("Detail: ", "        ");

            if (failed != null) {
                logger.error(MessageFormat.format("Response of {0} failed in {1,number}ms: {2}\n{3}\n", new Object[] { requestString, new Long(duration), failed.getLocalizedMessage(), detail }));
            } else if (duration > threshold) {
                logger.warn(MessageFormat.format("Response of {0} returned in {1,number}ms\n{2}\n", new Object[] { requestString, new Long(duration), detail }));
            } else if (logger.isInfoEnabled()) {
                logger.info(MessageFormat.format("Response of {0} returned in {1,number}ms\n{2}\n", new Object[] { requestString, new Long(duration), detail }));
            }

            if (pagelogger.isInfoEnabled()) {
                String requestUrl = request.getRequestURI();
                pagelogger.info("(" + requestUrl + ",Y," + new Long(duration) + "ms)");
            }

            Profiler.reset();
        }

        if (failed != null) {
            if (failed instanceof Error) {
                throw (Error) failed;
            } else if (failed instanceof RuntimeException) {
                throw (RuntimeException) failed;
            } else if (failed instanceof IOException) {
                throw (IOException) failed;
            } else if (failed instanceof ServletException) {
                throw (ServletException) failed;
            }
        }
    }

}
