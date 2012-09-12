/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认上下文
 * 
 * @author shizihu
 * @version $Id: DefaultContext.java, v 0.1 2012-9-4 02:12:58 shizihu Exp $
 */
public class DefaultContext extends ToString implements Context<String, Object> {
    private static final long         serialVersionUID = 2565436347603875625L;

    /** 数据窗口 */
    private final Map<String, Object> cxt              = new ConcurrentHashMap<String, Object>();

    /** 
     * @see com.atom.core.Context#set(java.lang.Object, java.lang.Object)
     */
    public DefaultContext set(String key, Object value) {
        cxt.put(key, value);
        return this;
    }

    /** 
     * @see com.atom.core.Context#get(java.lang.Object)
     */
    public Object get(String key) {
        return cxt.get(key);
    }

    /** 
     * @see com.atom.core.Context#clear()
     */
    public DefaultContext clear() {
        cxt.clear();
        return this;
    }

    /** 
     * @see com.atom.core.Context#remove(java.lang.Object)
     */
    public DefaultContext remove(String key) {
        cxt.remove(key);
        return this;
    }

}
