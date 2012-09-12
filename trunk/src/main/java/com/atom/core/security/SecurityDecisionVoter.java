/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 投票器
 * 
 * @author obullxl@gmail.com
 * @version $Id: SecurityDecisionVoter.java, 2012-9-9 下午5:48:46 Exp $
 */
public class SecurityDecisionVoter implements AccessDecisionVoter<Object> {

    /**
     * @see org.springframework.security.access.AccessDecisionVoter#supports(org.springframework.security.access.ConfigAttribute)
     */
    public boolean supports(ConfigAttribute attribute) {
        if ((attribute != null) && (attribute.getAttribute() != null)) {
            return true;
        }
        
        return false;
    }

    /**
     * @see org.springframework.security.access.AccessDecisionVoter#supports(java.lang.Class)
     */
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * @see org.springframework.security.access.AccessDecisionVoter#vote(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
     */
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        // ROOT用户
        if(OperationContextUtils.isRootUser(authentication)) {
            return ACCESS_GRANTED;
        }
        
        // 其它用户
        int result = ACCESS_ABSTAIN;
        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

        for (ConfigAttribute attribute : attributes) {
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                // Attempt to find a matching granted authority
                for (GrantedAuthority authority : authorities) {
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return result;
    }
    
    private Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }

}
