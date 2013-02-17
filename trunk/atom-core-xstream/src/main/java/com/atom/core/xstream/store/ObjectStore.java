/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.xstream.store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.atom.core.xstream.XStreamHolder;
import com.thoughtworks.xstream.XStream;

/**
 * 对象存储。
 * <p/>
 * 本存储器区别于{@code Store}存储器：本对象是单个对象，而{@code Store}是存储数据列表，就数据表！
 * 
 * @author obullxl@gmail.com
 * @version $Id: ObjectStore.java, V1.0.1 2013-2-10 下午8:58:44 $
 */
public class ObjectStore {
    /** 可重入锁 */
    private static final Lock lock = new ReentrantLock();

    /** 数据对象 */
    private Object            object;

    /** 文件路径 */
    private String            filePath;

    /**
     * 获取XStream对象
     */
    public static XStream findXStream() {
        return XStreamHolder.get();
    }

    /**
     * 初始化对象存储器
     */
    public void init() {
        lock.lock();
        try {
            if (StringUtils.isBlank(this.filePath)) {
                throw new RuntimeException("Store初始化存储器失败，文件路径为空！");
            }

            File file = new File(this.getFilePath());
            if (file.exists()) {
                this.object = findXStream().fromXML(file);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取数据内容
     */
    public Object findObject() {
        return this.object;
    }

    /**
     * 更新对象内容
     */
    public void update(Object object) {
        this.object = object;
    }

    /**
     * 保存存储器
     */
    public void persist() {
        lock.lock();
        try {
            File file = new File(this.getFilePath());
            File root = file.getParentFile();
            if (!root.exists()) {
                root.mkdirs();
            }

            OutputStream out = new FileOutputStream(file);
            try {
                findXStream().toXML(this.object, out);
            } finally {
                IOUtils.closeQuietly(out);
            }
        } catch (Exception e) {
            throw new RuntimeException("Store保存对象内容异常！");
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取文件路径
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * 设置数据文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
