/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.xstream.store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.ids.LongID;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.xstream.XStreamHolder;
import com.thoughtworks.xstream.XStream;

/**
 * 存储器基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseStore.java, 2012-8-18 下午8:25:49 Exp $
 */
public abstract class BaseStore<T extends LongID> implements Store<T> {
    /** 可重入读写锁 */
    private static final ReadWriteLock lock  = new ReentrantReadWriteLock();
    /** 数据内容 */
    private final Map<Long, T>         store = new ConcurrentHashMap<Long, T>();
    /** ID */
    private final AtomicLong           aid   = new AtomicLong();

    /** 文件路径 */
    private String                     filePath;

    /** 
     * @see com.atom.core.xstream.store.Store#getFilePath()
     */
    public String getFilePath() {
        return this.filePath;
    }

    /** 
     * @see com.atom.core.xstream.store.Store#setFilePath(java.lang.String)
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * 获取XStream对象
     */
    public static XStream findXStream() {
        return XStreamHolder.get();
    }

    /**
     * 初始化工作
     */
    public abstract void initExt();

    /** 
     * @see com.atom.core.xstream.store.Store#init()
     */
    @SuppressWarnings("unchecked")
    public boolean init() {
        boolean rtn = true;

        lock.writeLock().lock();
        try {
            this.initExt();

            if (StringUtils.isBlank(this.filePath)) {
                throw new RuntimeException("Store初始化存储器失败，文件路径为空！");
            }

            findXStream().alias("LongID", LongID.class);

            File file = new File(this.getFilePath());
            if (file.exists()) {
                this.store.putAll((Map<Long, T>) findXStream().fromXML(file));
                this.aid.set(this.findMaxID());
            }
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "初始化数据存储异常.", e);
        } finally {
            lock.writeLock().unlock();
        }

        return rtn;
    }

    /** 
     * @see com.atom.core.xstream.store.Store#save()
     */
    public boolean save() {
        boolean rtn = true;

        lock.writeLock().lock();
        try {
            File file = new File(this.getFilePath());
            File root = file.getParentFile();
            if (!root.exists()) {
                root.mkdirs();
            }

            OutputStream out = new FileOutputStream(file);
            try {
                findXStream().toXML(this.store, out);
            } finally {
                IOUtils.closeQuietly(out);
            }
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "保存异常.", e);
        } finally {
            lock.writeLock().unlock();
        }

        return rtn;
    }

    /** 
     * @see com.atom.core.xstream.store.Store#create(com.atom.core.lang.ids.ID)
     */
    public boolean create(T value) {
        boolean rtn = true;

        lock.writeLock().lock();
        try {
            value.removeSaveFlag();
            
            value.setId(this.aid.incrementAndGet());
            this.update(value);
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "创建异常.", e);
        } finally {
            lock.writeLock().unlock();
        }

        return rtn;
    }

    /** 
     * @see com.atom.core.xstream.store.Store#find(long)
     */
    public T find(long id) {
        lock.readLock().lock();
        try {
            return this.store.get(id);
        } finally {
            lock.readLock().unlock();
        }
    }

    /** 
     * @see com.atom.core.xstream.store.Store#findAll()
     */
    public List<T> findAll() {
        lock.readLock().lock();
        try {
            return new ArrayList<T>(this.store.values());
        } finally {
            lock.readLock().unlock();
        }
    }

    /** 
     * @see com.atom.core.xstream.store.Store#clear()
     */
    public boolean clear() {
        boolean rtn = true;

        lock.writeLock().lock();
        try {
            this.store.clear();
            rtn = this.save();
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "清除异常.", e);
        } finally {
            lock.writeLock().unlock();
        }

        return rtn;
    }

    /** 
     * @see com.atom.core.xstream.store.Store#remove(long)
     */
    public boolean remove(long id) {
        boolean rtn = true;

        lock.writeLock().lock();
        try {
            this.store.remove(id);
            rtn = this.save();
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "删除异常, ID[" + id + "].", e);
        } finally {
            lock.writeLock().unlock();
        }

        return rtn;
    }

    /** 
     * @see com.atom.core.xstream.store.Store#update(com.atom.core.lang.ids.ID)
     */
    public boolean update(T value) {
        boolean rtn = true;

        lock.writeLock().lock();
        try {
            value.removeSaveFlag();
            
            this.store.put(value.getId(), value);
            rtn = this.save();
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "更新异常, Value[" + value + "].", e);
        } finally {
            lock.writeLock().unlock();
        }

        return rtn;
    }

    /**
     * 子类类名
     */
    private String clazz() {
        return this.getClass().getSimpleName();
    }

    /**
     * 获取目前最大ID值
     */
    private long findMaxID() {
        long max = 0L;

        for (long tid : this.store.keySet()) {
            max = Math.max(max, tid);
        }

        return max;
    }

}
