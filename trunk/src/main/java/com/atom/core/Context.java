/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core;

/**
 * 上下文件接口
 * 
 * @author shizihu
 * @version $Id: Context.java, v 0.1 2012-9-6 下午01:26:03 shizihu Exp $
 */
public interface Context<K, V> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * 设置数据
     */
    public Context<K, V> set(K key, V value);

    /**
     * 获取数据
     */
    public V get(K key);

    /**
     * 清空所有数据
     */
    public Context<K, V> clear();

    /**
     * 根据KEY删除数据
     */
    public Context<K, V> remove(K key);

}
