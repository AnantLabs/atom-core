/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * TabPane基础视图，扩展并增加了通用方法。
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseTabPane.java, V1.0.1 2013-2-6 下午1:37:47 $
 */
public abstract class BaseTabPane {

    /**
     * CTOR
     */
    public BaseTabPane() {
    }

    /**
     * 获取TabPane对象，由顶层父类提供
     */
    public abstract TabPane findTabPane();

    /**
     * 获取主面Tab标签，该标签不能被关闭
     */
    public abstract Tab findHomePageTab();

    /**
     * 根据用户数据查询Tab标签，如果不存在，则返回NULL对象
     */
    public final Tab findTab(Object userData) {
        if (userData == null) {
            return null;
        }

        TabPane tabpane = this.findTabPane();
        if (tabpane == null) {
            return null;
        }

        for (Tab tab : tabpane.getTabs()) {
            Object tabUserData = this.findTabUserData(tab);
            if (tabUserData == null) {
                return null;
            }

            if (userData.equals(tabUserData)) {
                // 找到
                return tab;
            }
        }

        // 没有找到
        return null;
    }

    /**
     * 根据Tab标签用户数据检测标签是否已经存在
     */
    public final boolean existTab(Object userData) {
        return (this.findTab(userData) != null);
    }

    /**
     * 比较2个标签是否相等：
     * <p/>
     * 1、任意一个Tab为NULL，则不相等；<br/>
     * 2、任意一个Tab的用户数据为NULL，则不相等；<br/>
     * 3、2个Tab的用户类型相等且值相等，即equals()返回true，则认为2个Tab相等；
     */
    public final boolean isSameTab(Tab one, Tab two) {
        Object oneObj = this.findTabUserData(one);
        Object twoObj = this.findTabUserData(two);
        if (oneObj == null || twoObj == null) {
            return false;
        }

        return oneObj.equals(twoObj);
    }

    /**
     * 根据用户数据激活1个标签
     */
    public final void activeTab(Object userData) {
        if (userData == null) {
            return;
        }

        Tab tab = this.findTab(userData);
        if (tab == null) {
            // 不存在Tab标签
            return;
        }

        if (!tab.isSelected()) {
            // 激活
            this.findTabPane().getSelectionModel().select(tab);
        }
    }

    /**
     * 增加并且激活1个Tab标签
     */
    public final void addAndActiveTab(Tab tab) {
        if (tab == null) {
            return;
        }

        TabPane tabpane = this.findTabPane();
        if (tabpane == null) {
            return;
        }

        tabpane.getTabs().add(tab);
        tabpane.getSelectionModel().select(tab);
    }

    /**
     * 增加唯一性Tab标签。
     * <p/>
     * 1、若Tab标签不存在，则增加该标签，并且激活；<br/>
     * 2、若Tab标签已经存在，则删除原标签，增加新标签，并且激活；
     */
    public final void addUniqueTab(Tab uniqueTab) {
        TabPane tabpane = this.findTabPane();
        if (tabpane == null) {
            return;
        }

        Object userData = this.findTabUserData(uniqueTab);
        if (userData == null) {
            return;
        }

        Tab existTab = this.findTab(userData);
        if (existTab != null) {
            // 删除标签
            tabpane.getTabs().removeAll(existTab);
        }

        // 增加并激活标签
        this.addAndActiveTab(uniqueTab);
    }

    /**
     * 获取标签用户数据，如果不存在，则返回为NULL对象
     * <p/>
     * 标签用户数据参见{@link com.atom.core.lang.enums.EnumBase}
     */
    @SuppressWarnings("unchecked")
    public final <T> T findTabUserData(Tab tab) {
        if (tab == null) {
            return null;
        }

        Object object = tab.getUserData();
        if (object == null) {
            return null;
        }

        return (T) object;
    }

    /**
     * 删除Tab标签
     */
    public final void removeTab(Tab tab) {
        if(tab == null) {
            return;
        }
        
        TabPane tabpane = this.findTabPane();
        if (tabpane == null) {
            return;
        }

        tabpane.getTabs().removeAll(tab);
    }

    /**
     * 根据用户数据删除Tab标签
     */
    public final Tab removeTab(Object userData) {
        if(userData == null) {
            return null;
        }

        Tab tab = this.findTab(userData);
        this.removeTab(tab);

        return tab;
    }

}
