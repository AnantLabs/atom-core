/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 * 公共的角色
 * 
 * @author obullxl@gmail.com
 * @version $Id: SecurityRoleEnum.java, 2012-9-9 下午4:49:51 Exp $
 */
public enum SecurityRoleEnum implements GrantedAuthority {
    //
    ROLE_HOLD("业务权限"),
    //
    ROLE_LOGIN("登录权限"),
    //
    ;

    /** 授权对象 */
    private final String desp;

    private SecurityRoleEnum(String desp) {
        this.desp = desp;
    }

    /**
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    public String getAuthority() {
        return this.name();
    }

    /**
     * 根据代码或授权获取授权对象
     */
    public static final SecurityRoleEnum findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (SecurityRoleEnum authority : values()) {
            if (StringUtils.equalsIgnoreCase(authority.getAuthority(), code)) {
                return authority;
            }
        }

        return null;
    }

    // ~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ ///

    public String getDesp() {
        return desp;
    }

}
