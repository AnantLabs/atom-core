/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import com.atom.core.EnumBase;

/**
 * 公共的角色
 * 
 * @author obullxl@gmail.com
 * @version $Id: SecurityRoleEnum.java, 2012-9-9 下午4:49:51 Exp $
 */
public enum SecurityRoleEnum implements EnumBase {
    //
    // ROLE_HOLD("业务权限"),
    //
    ROLE_LOGIN("登录权限"),
    //
    ;

    /** 描述 */
    private final String desp;

    /**
     * CTOR
     */
    private SecurityRoleEnum(String desp) {
        this.desp = desp;
    }

    /**
     * @see com.atom.core.EnumBase#code()
     */
    public String code() {
        return this.name();
    }

    /**
     * @see com.atom.core.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

    /**
     * 构建授权信息
     */
    public GrantedAuthority toAuthority() {
        return new SecurityAuthority(this.code());
    }

    /**
     * 根据代码或授权获取授权对象
     */
    public static final SecurityRoleEnum findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (SecurityRoleEnum enumm : values()) {
            if (StringUtils.equalsIgnoreCase(enumm.code(), code)) {
                return enumm;
            }
        }

        return null;
    }

}
