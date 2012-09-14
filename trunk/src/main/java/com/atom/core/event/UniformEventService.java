/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.event;

/**
 * 统一事件服务接口
 * <p/>
 * 1、发送统一事件功能；<br/>
 * 2、处理统一事件功能；<br/>
 * 
 * @author shizihu
 * @version $Id: UniformEventService.java, v 0.1 2012-9-14 上午09:45:59 shizihu Exp $
 */
public interface UniformEventService {

    /**
     * 处理统一事件
     * 
     * @param message 统一事件
     */
    // public void processUniformEvent(UniformEvent message);
    
    /**
     * 发送统一事件
     * 
     * @param message 统一事件
     */
    public void publishUniformEvent(UniformEvent message);
    
}
