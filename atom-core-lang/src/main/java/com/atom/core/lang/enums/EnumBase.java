/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.enums;

/**
 * 枚举基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: EnumBase.java, 2012-8-18 下午8:25:49 Exp $
 */
public interface EnumBase {

    /**
     * 枚举ID
     */
    public int id();

    /**
     * 枚举名称
     */
    public String name();

    /**
     * 枚举代码
     */
    public String code();

    /**
     * 枚举描述
     */
    public String desp();

}
