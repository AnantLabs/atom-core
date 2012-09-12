/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.atom.core;

/**
 * 日志常量
 * 
 * @author obullxl@gmail.com
 * @version $Id: LoggerConstants.java, 2011-12-26 下午8:41:38 Exp $
 */
public class LogConsts {
    /** 应用名称 */
    private static final String APP     = Consts.APP_NAME;

    /**
     * 日志名：核心
     */
    public static final String  CORE    = APP + "-CORE";

    /**
     * 日志名：公用
     */
    public static final String  DEFAULT = APP + "-DEFAULT";

    /**
     * 日志名：DAL业务
     */
    public static final String  DAL     = APP + "-DAL";

    /**
     * 日志名：Biz业务
     */
    public static final String  BIZ     = APP + "-BIZ";

    /**
     * 日志名：Web业务
     */
    public static final String  WEB     = APP + "-WEB";

    /**
     * 日志名：Web摘要
     */
    public static final String  DIGEST  = APP + "-DIGEST";

    /**
     * 日志名：工具类
     */
    public static final String  UTIL    = APP + "-UTIL";

}
