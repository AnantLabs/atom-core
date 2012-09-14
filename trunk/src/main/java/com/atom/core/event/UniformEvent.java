/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.event;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

import com.atom.core.ToStringStyle;
import com.atom.core.utils.HostUtils;

/**
 * 统一事件对象
 * 
 * @author shizihu
 * @version $Id: UniformEvent.java, v 0.1 2012-9-14 上午09:20:07 shizihu Exp $
 */
public class UniformEvent extends ApplicationEvent implements Serializable, EventConsts {
    private static final long   serialVersionUID = -7814547482391081730L;

    /** 事件UUID */
    private String              id;

    /** 事件主题 */
    private String              topic;

    /** 事件编码 */
    private String              evtCode;

    /** 事件产生时间 */
    private Date                gmtEvtTime       = new Date();

    /** 当前事件主体 */
    private Principal           principal;

    /** 机器名 */
    private String              hostName;

    /** 事件扩展属性 */
    private Map<String, Object> extMap           = new HashMap<String, Object>();

    /**
     * CTOR
     */
    public UniformEvent(Object source) {
        super(source);
        Assert.notNull(source, "[统一事件]-消息体对象不能为NULL.");

        this.id = UUID.randomUUID().toString();
        this.hostName = HostUtils.getHost().getName();
    }

    /**
     * CTOR
     */
    public UniformEvent(Object source, String topic, String evtCode) {
        this(source);
        Assert.notNull(source, "[统一事件]-消息体对象不能为NULL.");

        this.topic = topic;
        this.evtCode = evtCode;
    }

    /**
     * 设置扩展属性
     */
    public void setProperty(String key, Object value) {
        this.getExtMap().put(key, value);
    }

    /** 
     * @see java.util.EventObject#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.STYLE);
    }

    // ~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~ //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEvtCode() {
        return evtCode;
    }

    public void setEvtCode(String evtCode) {
        this.evtCode = evtCode;
    }

    public Date getGmtEvtTime() {
        return gmtEvtTime;
    }

    public void setGmtEvtTime(Date gmtEvtTime) {
        this.gmtEvtTime = gmtEvtTime;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Map<String, Object> getExtMap() {
        if (this.extMap == null) {
            this.extMap = new HashMap<String, Object>();
        }

        return extMap;
    }

    public void setExtMap(Map<String, Object> extMap) {
        if (extMap == null || extMap.isEmpty()) {
            return;
        }

        this.getExtMap().clear();
        this.getExtMap().putAll(extMap);
    }

}
