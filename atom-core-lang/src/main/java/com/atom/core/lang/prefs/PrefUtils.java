/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.prefs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author shizihu
 * @version $Id: PrefUtils.java, v 0.1 2013-1-28 上午10:16:40 shizihu Exp $
 */
public class PrefUtils {
    private static final ReadWriteLock        _lock = new ReentrantReadWriteLock();
    private static final Map<String, PrefMap> _map  = new ConcurrentHashMap<String, PrefMap>();

    public static final String get(String catg, String key) {
        _lock.readLock().lock();
        try {
            return ensure(catg).get(key);
        } finally {
            _lock.readLock().unlock();
        }
    }

    public static final void put(String catg, String key, String value) {
        _lock.writeLock().lock();
        try {
            ensure(catg).put(key, value);
        } finally {
            _lock.writeLock().unlock();
        }
    }

    public static final String remove(String catg, String key) {
        _lock.writeLock().lock();
        try {
            return ensure(catg).remove(key);
        } finally {
            _lock.writeLock().unlock();
        }
    }

    private static final PrefMap ensure(String category) {
        PrefMap pref = _map.get(category);
        if (pref == null) {
            pref = new PrefMap(category);
            _map.put(category, pref);
        }

        return pref;
    }

}
