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
    private static boolean           debugEnable = true;
    private static boolean           infoEnable  = true;

    private static final PrintStream out         = System.out;
    private static final PrintStream err         = System.err;
    private static final Logger      logger      = Logger.getLogger("LOG");

    /**
     * 设置是否打印Debug级别日志
     */
    public static void setDebugEnable(boolean debug) {
        debugEnable = debug;
    }

    /**
     * 设置是否打印Info级别日志
     */
    public static void setInfoEnable(boolean info) {
        infoEnable = info;
    }

    public static void debug(String msg) {
        if (debugEnable) {
            out.println(msg);
            logger.log(Level.FINER, msg);
        }
    }

    public static void debug(String msg, Throwable e) {
        if (debugEnable) {
            out.println(msg);
            e.printStackTrace();

            logger.log(Level.FINER, msg);
        }
    }

    public static void info(String msg) {
        if (infoEnable) {
            out.println(msg);
            logger.log(Level.INFO, msg);
        }
    }
    
    public static void info(String msg, Throwable e) {
        if (infoEnable) {
            out.println(msg);
            e.printStackTrace();
            
            logger.log(Level.INFO, msg);
        }
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

    public static void error(String msg) {
        err.println(msg);

        logger.log(Level.SEVERE, msg);
    }
    
    public static void error(String msg, Throwable e) {
        err.println(msg);
        e.printStackTrace();

        logger.log(Level.SEVERE, msg, e);
    }
    
}
