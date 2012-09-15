/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.utils;

/**
 * 用户工具类
 * 
 * @author shizihu
 * @version $Id: UserUtils.java, v 0.1 2012-9-7 下午9:10:11 shizihu Exp $
 */
public final class UserUtils {

    public static final long   SYS_USER_ID   = 0L;

    public static final String SYS_USER_NAME = "系统";

    /**
     * 系统用户UID
     */
    public static final long findSystemUserID() {
        return SYS_USER_ID;
    }

    /**
     * 系统用户名
     */
    public static final String findSystemUserName() {
        return SYS_USER_NAME;
    }

}
