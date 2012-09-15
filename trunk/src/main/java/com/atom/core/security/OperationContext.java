/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.security;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.atom.core.ToString;

/**
 * 操作上下文
 * <p/>
 * 参照：{@link org.springframework.security.core.userdetails.User}
 * 
 * @author obullxl@gmail.com
 * @version $Id: OperationContext.java, 2012-9-9 下午4:45:23 Exp $
 */
public class OperationContext extends ToString implements UserDetails, CredentialsContainer, Principal {
    private static final long           serialVersionUID      = -7370639572728540305L;

    /** 用户ID */
    private long                        userId;
    /** 用户名 */
    private String                      username;
    /** 密码 */
    private String                      password;
    /** 权限 */
    private final Set<GrantedAuthority> authorities           = new HashSet<GrantedAuthority>();

    /** 用户是否有效 */
    private boolean                     enabled               = true;
    /** 用户是否解锁 */
    private boolean                     accountNonLocked      = true;
    /** 用户是否未过期 */
    private boolean                     accountNonExpired     = true;
    /** 凭据是否未过期 */
    private boolean                     credentialsNonExpired = true;

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * @see java.security.Principal#getName()
     */
    public String getName() {
        return this.getUsername();
    }

    /**
     * @see org.springframework.security.core.CredentialsContainer#eraseCredentials()
     */
    public void eraseCredentials() {
        this.password = StringUtils.EMPTY;
    }

    // ~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public void setAuthorities(Collection<GrantedAuthority> collection) {
        this.authorities.clear();
        this.authorities.addAll(collection);
    }

    public void addAuthority(GrantedAuthority authority) {
        this.authorities.add(authority);
    }

    // ~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

}
