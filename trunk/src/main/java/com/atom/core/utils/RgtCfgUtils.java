/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.utils;

import com.atom.ebank.common.enums.RightCategoryEnum;
import com.atom.ebank.common.enums.RightFunctionEnum;

/**
 * 权限配置工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: RgtCfgUtils.java, 2012-8-19 下午4:54:27 Exp $
 */
public final class RgtCfgUtils {

    /**
     * 组装权限代码
     */
    public static final String findRgtCode(RightFunctionEnum func, RightCategoryEnum catg) {
        return func.code() + "-" + catg.code();
    }

}
