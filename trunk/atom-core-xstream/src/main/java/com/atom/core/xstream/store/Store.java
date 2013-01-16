/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.xstream.store;

import java.util.List;

import com.atom.core.lang.ids.ID;

/**
 * 存储器接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: Store.java, 2012-8-18 下午8:25:49 Exp $
 */
public interface Store<T extends ID<Long>> {

    /**
     * 初始化存储器
     */
    public boolean init();

    /**
     * 保存存储器
     */
    public boolean save();

    /**
     * 获取文件路径
     */
    public String getFilePath();

    /**
     * 设置数据文件路径
     */
    public void setFilePath(String filePath);

    /**
     * 增加一个对象
     */
    public boolean create(T value);

    /**
     * 查找一个对象
     */
    public T find(long id);

    /**
     * 查找所有对象
     */
    public List<T> findAll();

    /**
     * 清除数据
     */
    public boolean clear();
    
    /**
     * 删除对象
     */
    public boolean remove(long id);

    /**
     * 更新数据
     */
    public boolean update(T value);

}
