/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.atom.core.web.servlet.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.atom.core.CfgConsts;
import com.atom.core.utils.CfgUtils;

/**
 * Log4j日志监听器
 * 
 * @author obullxl@gmail.com
 * @version $Id: Log4jConfigListener.java, 2011-12-26 下午8:06:04 Exp $
 */
public class Log4jConfigListener extends org.springframework.web.util.Log4jConfigListener {

    /** 
     * @see org.springframework.web.util.Log4jConfigListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        // 解析日志根目录
        String path = event.getServletContext().getRealPath(".");
        path = FilenameUtils.normalizeNoEndSeparator(path + "/logs");

        // 创建目录
        try {
            FileUtils.forceMkdir(new File(path));
        } catch (Exception e) {
            event.getServletContext().log("[初始化]-创建Log4j日志根目录失败！", e);
        }

        // 放入上下文中
        CfgUtils.setPorperty(CfgConsts.LOG_ROOT, path);

        // 其它初始化
        super.contextInitialized(event);
    }

}
