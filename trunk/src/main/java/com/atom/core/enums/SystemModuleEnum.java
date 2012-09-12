/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.enums;

import org.apache.commons.lang.StringUtils;

import com.atom.core.EnumBase;

/**
 * 系统模块枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemModuleEnum.java, 2012-9-12 下午9:02:23 Exp $
 */
public enum SystemModuleEnum implements EnumBase {
    //
    SYSM("系统参数"),
    //
    MENU("菜单配置"),
    //
    USER("用户管理"),
    //
    ;

    /** 描述 */
    private final String desp;

    private SystemModuleEnum(String desp) {
        this.desp = desp;
    }

    public static final SystemModuleEnum findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (SystemModuleEnum enumm : values()) {
            if (StringUtils.equalsIgnoreCase(code, enumm.code())) {
                return enumm;
            }
        }

        return null;
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

}
