/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.xstream.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.atom.core.lang.utils.LogUtils;

/**
 * 存储器工厂
 * 
 * @author obullxl@gmail.com
 * @version $Id: StoreFactory.java, 2012-8-18 下午8:25:49 Exp $
 */
public class StoreFactory {
    /** 实例单例 */
    private static final StoreFactory     factory = new StoreFactory();
    /** 数据存储器 */
    private final Map<Class<?>, Store<?>> stores  = new ConcurrentHashMap<Class<?>, Store<?>>();

    /**
     * 获取工厂
     */
    public static final StoreFactory get() {
        return factory;
    }

    private StoreFactory() {
    }

    /**
     * 初始化
     */
    public void init() {
        for (Store<?> store : this.stores.values()) {
            store.init();
        }
    }

    /**
     * 关闭存储器
     */
    public void stop() {
        for (Store<?> store : this.stores.values()) {
            store.save();
        }
    }

    /**
     * 查询存储器
     */
    @SuppressWarnings("unchecked")
    public <T extends Store<?>> T findStore(Class<T> clazz) {
        Store<?> store = this.stores.get(clazz);

        if (store == null) {
            try {
                store = (Store<?>) clazz.newInstance();
                this.registStore(store);
                store.init();
            } catch (Exception e) {
                LogUtils.error("初始化存储器异常, Clazz[" + clazz + "].", e);
            }
        }

        return (T) store;
    }
    
    /**
     * 注册存储器
     */
    public void registStore(Store<?> store) {
        this.stores.put(store.getClass(), store);
    }
    
    /**
     * 取消注册存储器
     */
    public void unregistStore(Class<?> clazz) {
        this.stores.remove(clazz);
    }

}
