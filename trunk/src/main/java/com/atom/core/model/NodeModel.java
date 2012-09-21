/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atom.core.ToString;

/**
 * 节点模型
 * 
 * @author shizihu
 * @version $Id: NodeModel.java, v 0.1 2012-9-13 上午09:50:04 shizihu Exp $
 */
public class NodeModel extends ToString implements NodeConst {
    private static final long   serialVersionUID = 2545193236502133681L;

    /** 名称 */
    private String              text;

    /** 节点属性 */
    private Map<String, String> extMap;

    /** 子菜单 */
    private List<NodeModel>     children;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChildren(List<NodeModel> children) {
        this.children = children;
    }

    public Map<String, String> getExtMap() {
        if (this.extMap == null) {
            this.extMap = new HashMap<String, String>();
        }

        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    public List<NodeModel> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<NodeModel>();
        }

        return children;
    }

}
