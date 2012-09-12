/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * 安全授权对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: SecurityAuthority.java, 2012-9-9 下午4:49:51 Exp $
 */
public enum SecurityAuthority implements GrantedAuthority {
    // 所有权限
    // AUTH_FULL(AuthenticatedVoter.IS_AUTHENTICATED_FULLY),
    //
    // 系统参数-查看
    // SYS_CFG_VIEW(RightFunctionEnum.SYS_CFG.code() + RightCategoryEnum.QUERY.code());
    //
    ;

    /** 授权对象 */
    private final String authority;

    private SecurityAuthority(String authority) {
        Assert.notNull(authority, "[授权对象]-凭证不能为空!");

        this.authority = authority;
    }

    /**
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    public String getAuthority() {
        return this.authority;
    }

    /**
     * 根据代码或授权获取授权对象
     */
    public static final SecurityAuthority findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (SecurityAuthority authority : values()) {
            if (StringUtils.equalsIgnoreCase(authority.getAuthority(), code)) {
                return authority;
            }
        }

        return null;
    }

}
