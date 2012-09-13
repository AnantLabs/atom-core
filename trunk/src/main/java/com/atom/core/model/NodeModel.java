/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atom.core.ToString;

/**
 * 节点模型
 * 
 * @author shizihu
 * @version $Id: NodeModel.java, v 0.1 2012-9-13 上午09:50:04 shizihu Exp $
 */
public class NodeModel extends ToString implements NodeConst {
    private static final long serialVersionUID = 2545193236502133681L;

    /** ID */
    private String            id;

    /** 上级菜单ID */
    private String            parentId;

    /** 名称 */
    private String            text;

    /** 排序 */
    private String            sort;

    /** 菜单链接 */
    private String            page;

    /** 是否为叶子节点 */
    private boolean           leaf             = true;

    /** 如果为父节点，是否展开 */
    private boolean           expanded         = true;

    /** 是否选中 */
    private Boolean           checked;

    /** 节点图标：关闭图标/叶子图标 */
    private String            icon;

    /** 节点图标2：打开图标 */
    private String            iconExt;

    /** 描述信息-标题 */
    private String            qtitle;

    /** 描述信息-内容 */
    private String            qtip;

    /** 创建时间 */
    private Date              gmtCreate;

    /** 修改时间 */
    private Date              gmtModify;

    /** 子菜单 */
    private List<NodeModel>   children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconExt() {
        return iconExt;
    }

    public void setIconExt(String iconExt) {
        this.iconExt = iconExt;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public String getQtip() {
        return qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public List<NodeModel> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<NodeModel>();
        }

        return children;
    }

    public void setChildren(List<NodeModel> children) {
        this.children = children;
    }

}
