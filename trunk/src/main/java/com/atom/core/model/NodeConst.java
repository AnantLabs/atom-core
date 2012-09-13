/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.model;

/**
 * 节点常量
 * 
 * @author shizihu
 * @version $Id: NodeConst.java, v 0.1 2012-9-13 下午01:22:41 shizihu Exp $
 */
public interface NodeConst {

    /** ID */
    String ID         = "id";

    /** 上级菜单ID */
    String PARENT_ID  = "parentId";

    /** 名称 */
    String TEXT       = "text";

    /** 排序 */
    String SORT       = "sort";

    /** 菜单链接 */
    String PAGE       = "page";

    /** 是否为叶子节点 */
    String IS_LEAF    = "leaf";

    /** 如果为父节点，是否展开 */
    String IS_EXPAND  = "expanded";

    /** 是否选中 */
    String IS_CHECK   = "checked";

    /** 节点图标：关闭图标/叶子图标 */
    String ICON       = "icon";

    /** 节点图标2：打开图标 */
    String ICON_EXT   = "iconExt";

    /** 描述信息-标题 */
    String QTITLE     = "qtitle";

    /** 描述信息-内容 */
    String QTIP       = "qtip";

    /** 创建时间 */
    String GMT_CREATE = "gmtCreate";

    /** 修改时间 */
    String GMT_MODIFY = "gmtModify";

}
