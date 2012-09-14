/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.event;

/**
 * 事件常量
 * 
 * @author shizihu
 * @version $Id: EventConsts.java, v 0.1 2012-9-14 上午09:23:27 shizihu Exp $
 */
public interface EventConsts {

    /**
     * 获取重试投递次数key, 首次投递为<code>0</code>
     */
    public static final String PROPS_DELIVERY_COUNT   = "^deliveryCount$";

    /**
     * 由消息中心投递到接收端的超时时间ms, 默认10000ms
     */
    public static final String POST_TO_CLIENT_TIMEOUT = "^postTimeout$";

    /**
     * 消息发送端发送消息到消息中心的超时时间ms, 默认3000ms
     */
    public static final String SEND_TO_SERVER_TIMEOUT = "^sendTimeout$";

    /**
     * 存活时间, 单位ms
     */
    public static final String TIME_TO_LIVE           = "^timeToLive$";

    /**
     * 延迟投递时间, 单位ms
     */
    public static final String POST_DELAY_TIME        = "^postDelayTime$";

}
