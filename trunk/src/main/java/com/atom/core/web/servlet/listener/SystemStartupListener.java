/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.atom.core.web.servlet.listener;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import com.atom.core.CfgConsts;
import com.atom.core.utils.CfgUtils;
import com.atom.core.utils.MD5Utils;

/**
 * 系统初始化监听器
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemStartupListener.java, 2011-12-26 下午8:17:48 Exp $
 */
public class SystemStartupListener implements ServletContextListener {

    /** 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
    }

    /** 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String path = context.getContextPath();
        if (StringUtils.endsWith(path, "/")) {
            path = StringUtils.substringBeforeLast(path, "/");
        }

        CfgUtils.setPorperty(CfgConsts.CFG_CTXT, path);
        CfgUtils.setPorperty(CfgConsts.CFG_STATIC, path + "/static");

        path = context.getRealPath(".");
        path = FilenameUtils.normalizeNoEndSeparator(path);
        CfgUtils.setPorperty(CfgConsts.CFG_ROOT, path);

        // 加载系统配置
        this.loadSysConfig(context);
        Map<String, String> cfgs = CfgUtils.getAll();
        
        // 设置全局变量
        for (Map.Entry<String, String> cfg : cfgs.entrySet()) {
            context.setAttribute(cfg.getKey(), cfg.getValue());
        }
        
        // 打印系统配置
        StringBuilder txt = new StringBuilder(1024);
        txt.append("系统参数：").append(SystemUtils.LINE_SEPARATOR);
        txt.append("+++++++++++++++++++++++++++++").append(SystemUtils.LINE_SEPARATOR);
        for (Map.Entry<String, String> cfg : cfgs.entrySet()) {
            txt.append("\t").append(cfg.getKey()).append(" = ").append(cfg.getValue());
            txt.append(SystemUtils.LINE_SEPARATOR);
        }
        txt.append("+++++++++++++++++++++++++++++").append(SystemUtils.LINE_SEPARATOR);

        System.err.println(txt.toString());
        sce.getServletContext().log(txt.toString());

        // 检查版权信息
        this.checkCopyright(context);
    }

    /**
     * 加载系统配置
     */
    private void loadSysConfig(ServletContext context) {
        try {
            String root = FilenameUtils.normalizeNoEndSeparator(context.getRealPath("."));
            String path = FilenameUtils.normalize(root + "/WEB-INF/config/system-config.xml");
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream(path));

            for (String key : prop.stringPropertyNames()) {
                context.setAttribute(key, prop.getProperty(key));
                CfgUtils.setPorperty(key, prop.getProperty(key));
            }
        } catch (Exception e) {
            context.log("[系统启动]-加载系统配置异常！", e);
        }
    }

    /**
     * 检查版权信息
     */
    private void checkCopyright(ServletContext context) {
        try {
            String cpName = CfgUtils.getString(CfgConsts.SYS_CP_NAME);
            String cpCode = CfgUtils.getString(CfgConsts.SYS_CP_CODE);
            String hashName = "U4" + StringUtils.upperCase(MD5Utils.password(cpName)) + "K3";

            if (StringUtils.isBlank(cpName) || StringUtils.isBlank(cpCode)
                || !StringUtils.equals(cpCode, hashName)) {
                context.log("[系统启动]-检查版权信息失败，系统无法启动！");
                System.exit(-1);
            }
        } catch (Exception e) {
            context.log("[系统启动]-检查版权信息异常，系统无法启动！", e);
            System.exit(-1);
        }
    }

}
