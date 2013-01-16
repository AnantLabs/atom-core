/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.xstream;

import com.thoughtworks.xstream.XStream;

/**
 * XStream持有器
 * 
 * @author obullxl@gmail.com
 * @version $Id: XStreamHolder.java, 2012-8-18 下午8:25:49 Exp $
 */
public class XStreamHolder {

    /** XStream */
    private static final XStream xstream = initXStream();

    private static XStream initXStream() {
        return new XStream();
    }
    
    /**
     * 获取XStream实例
     */
    public static XStream get() {
        return xstream;
    }

}
