/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.service;

import com.atom.core.model.NodeModel;

/**
 * 节点模型服务接口
 * 
 * @author shizihu
 * @version $Id: NodeModelService.java, v 0.1 2012-9-13 下午12:51:20 shizihu Exp $
 */
public interface NodeModelService {

    /**
     * 从XML配置文件转化为Java对象
     * 
     * @param config XML文件路径
     * 
     * @throws 如果解析失败，抛出异常
     */
    public NodeModel unmarshal(String config) throws Exception;

}
