/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 安全授权对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: SecurityAuthority.java, 2012-9-15 下午5:18:12 Exp $
 */
public class SecurityAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 4323543042580893267L;

    /** 授权对象 */
    private final String      authority;

    /**
     * CTOR
     */
    public SecurityAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    public String getAuthority() {
        return this.authority;
    }

}
