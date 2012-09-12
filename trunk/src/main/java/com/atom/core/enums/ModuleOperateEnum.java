/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.enums;

import org.apache.commons.lang.StringUtils;

import com.atom.core.EnumBase;

/**
 * 模块操作
 * 
 * @author obullxl@gmail.com
 * @version $Id: ModuleOperateEnum.java, 2012-9-12 下午9:06:12 Exp $
 */
public enum ModuleOperateEnum implements EnumBase {
    //
    QUERY("查看"),
    //
    CREATE("新增"),
    //
    REMOVE("删除"),
    //
    UPDATE("更新"),
    //
    EXPORT("导出"),
    //
    ;

    /** 描述 */
    private final String desp;

    private ModuleOperateEnum(String desp) {
        this.desp = desp;
    }

    public static final ModuleOperateEnum findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (ModuleOperateEnum enumm : values()) {
            if (StringUtils.equalsIgnoreCase(code, enumm.code())) {
                return enumm;
            }
        }

        return null;
    }

    public static final String findText(String code) {
        ModuleOperateEnum enumm = findByCode(code);

        if (enumm != null) {
            return enumm.desp();
        }

        return code;
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
