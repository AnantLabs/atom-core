/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 默认日志工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: LogUtils.java, 2012-8-18 下午8:25:49 Exp $
 */
public class LogUtils {
    private static final PrintStream out = System.out;
    private static final PrintStream err = System.err;
    
    private static final Logger logger = Logger.getLogger("LogUtils");

    public static void info(String msg) {
        out.println(msg);
        logger.log(Level.INFO, msg);
    }

    public static void warn(String msg) {
        err.println(msg);
        logger.log(Level.WARNING, msg);
    }

    public static void warn(String msg, Throwable e) {
        err.println(msg);
        e.printStackTrace();

        logger.log(Level.WARNING, msg, e);
    }

    public static void error(String msg, Throwable e) {
        err.println(msg);
        e.printStackTrace();

        logger.log(Level.SEVERE, msg, e);
    }
}
