/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.event;

/**
 * 事件订阅端监听器
 * 
 * @author shizihu
 * @version $Id: UniformEventListener.java, v 0.1 2012-9-14 上午09:43:32 shizihu Exp $
 */
public interface UniformEventListener {

    /**
     * 可以接收的事件主题
     */
    public String[] acceptTopics();
    
    /**
     * 可以接收的事件编码
     */
    public String[] acceptEvtCodes();
    
    /**
     * 事件接收触发
     * 
     * @param message 统一事件
     */
    public void onUniformEvent(final UniformEvent message);
    
}
