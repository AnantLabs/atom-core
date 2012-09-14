/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.event.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import com.atom.core.LogConsts;
import com.atom.core.event.UniformEvent;
import com.atom.core.event.UniformEventListener;
import com.atom.core.event.UniformEventService;

/**
 * Spring实现统一事件服务
 * 
 * @author shizihu
 * @version $Id: SpringUniformEventService.java, v 0.1 2012-9-14 上午09:51:06 shizihu Exp $
 */
public class SpringUniformEventService implements ApplicationContextAware,
                                      ApplicationListener<UniformEvent>, UniformEventService {
    private static final Logger                                  logger    = Logger
                                                                               .getLogger(LogConsts.EVENT);

    /** 应用上下文 */
    private ApplicationContext                                   applicationContext;

    /** 事件监听器 */
    private Map<String, Map<String, List<UniformEventListener>>> listeners = new ConcurrentHashMap<String, Map<String, List<UniformEventListener>>>();

    /** 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.initEventListeners();
    }

    /** 
     * @see com.atom.core.event.UniformEventService#publishUniformEvent(com.atom.core.event.UniformEvent)
     */
    public void publishUniformEvent(UniformEvent message) {
        this.applicationContext.publishEvent(message);
    }

    /** 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    public void onApplicationEvent(UniformEvent event) {
        this.processUniformEvent(event);
    }

    /**
     * 处理统一事件
     */
    private void processUniformEvent(UniformEvent message) {
        String topic = message.getTopic();
        String evtCode = message.getEvtCode();

        if (logger.isInfoEnabled()) {
            logger.info("[统一事件]-接收到统一事件, Message[" + message + "].");
        }

        if (StringUtils.isBlank(topic) || StringUtils.isBlank(evtCode)) {
            logger.warn("[统一事件]-丢弃统一事件, Message[" + message + "].");
        }

        // 处理消息
        List<UniformEventListener> dstObjs = this.findListeners(topic, evtCode);
        for (UniformEventListener listener : dstObjs) {
            listener.onUniformEvent(message);
        }
    }

    /**
     * 初始化监听器
     */
    private void initEventListeners() {
        Map<String, UniformEventListener> beans = this.applicationContext
            .getBeansOfType(UniformEventListener.class);
        if (beans == null || beans.isEmpty()) {
            return;
        }

        for (Map.Entry<String, UniformEventListener> entry : beans.entrySet()) {
            String name = entry.getKey();
            UniformEventListener listener = entry.getValue();

            logger.warn("[统一事件]-开始增加KEY[" + name + "], Listener[" + listener.getClass().getName()
                        + "].");

            String[] topics = listener.acceptTopics();
            String[] evtCodes = listener.acceptEvtCodes();
            for (String topic : topics) {
                Map<String, List<UniformEventListener>> listenerMap = this.listeners.get(topic);
                if (listenerMap == null) {
                    listenerMap = new ConcurrentHashMap<String, List<UniformEventListener>>();
                    this.listeners.put(topic, listenerMap);
                }

                for (String evtCode : evtCodes) {
                    List<UniformEventListener> listenerList = listenerMap.get(evtCode);
                    if (listenerList == null) {
                        listenerList = new ArrayList<UniformEventListener>();
                        listenerMap.put(evtCode, listenerList);
                    }

                    // 放入消息监听器
                    listenerList.add(listener);
                }
            }

            logger.warn("[统一事件]-增加KEY[" + name + "], Listener[" + listener.getClass().getName()
                        + "]完成.");
        }
    }

    /**
     * 获取统一事件监听器列表
     */
    private List<UniformEventListener> findListeners(String topic, String evtCode) {
        Map<String, List<UniformEventListener>> listenerMap = this.listeners.get(topic);
        if (listenerMap == null || listenerMap.isEmpty()) {
            return new ArrayList<UniformEventListener>();
        }

        List<UniformEventListener> listenerList = listenerMap.get(evtCode);
        if (listenerList == null || listenerList.isEmpty()) {
            return new ArrayList<UniformEventListener>();
        }

        return listenerList;
    }

}
