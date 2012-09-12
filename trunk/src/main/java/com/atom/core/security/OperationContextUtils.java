/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.atom.core.Consts;

/**
 * 操作上下文工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: OperationContextUtils.java, 2012-9-9 下午5:16:39 Exp $
 */
public class OperationContextUtils {

    /**
     * 获取操作上下文
     */
    public static final OperationContext getContext() {
        return (OperationContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 是否为Root用户
     */
    public static final boolean isRootUser(Authentication authentication) {
        if (StringUtils.equalsIgnoreCase(Consts.USER_ROOT, authentication.getName())) {
            return true;
        }

        return false;
    }

}
