/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.utils;

import org.springframework.security.core.GrantedAuthority;

import com.atom.core.EnumBase;

/**
 * 权限配置工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: RgtCfgUtils.java, 2012-8-19 下午4:54:27 Exp $
 */
public final class RgtCfgUtils {

    /** 权限代码分隔符：模块与操作分隔符 */
    public static final String RGT_SEP = "-";

    /**
     * 组装权限代码
     */
    public static final String findRgtCode(String module, String operate) {
        return module + RGT_SEP + operate;
    }

    /**
     * 组装权限代码
     */
    public static final String findRgtCode(EnumBase module, EnumBase operate) {
        return module.code() + RGT_SEP + operate.code();
    }
    
    public static final GrantedAuthority newGrantedAuthority(final String rgtCode) {
        return new GrantedAuthority(){
            private static final long serialVersionUID = 1L;

            public String getAuthority() {
                return rgtCode;
            }};
    }

}
