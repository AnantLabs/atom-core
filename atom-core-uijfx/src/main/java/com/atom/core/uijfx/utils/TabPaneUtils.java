/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.utils;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Tab工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: TabUtils.java, V1.0.1 2013-2-7 下午7:39:16 $
 */
public final class TabPaneUtils {

    /**
     * 根据用户数据查询Tab标签，如果不存在，则返回NULL对象
     */
    public static Tab findTab(TabPane tabpane, Object userData) {
        if (userData == null) {
            return null;
        }

        if (tabpane == null) {
            return null;
        }

        for (Tab tab : tabpane.getTabs()) {
            Object tabUserData = findTabUserData(tab);
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
    public static boolean existTab(TabPane tabpane, Object userData) {
        return (findTab(tabpane, userData) != null);
    }

    /**
     * 比较2个标签是否相等：
     * <p/>
     * 1、任意一个Tab为NULL，则不相等；<br/>
     * 2、任意一个Tab的用户数据为NULL，则不相等；<br/>
     * 3、2个Tab的用户类型相等且值相等，即equals()返回true，则认为2个Tab相等；
     */
    public static boolean isSameTab(Tab one, Tab two) {
        Object oneObj = findTabUserData(one);
        Object twoObj = findTabUserData(two);
        if (oneObj == null || twoObj == null) {
            return false;
        }

        return oneObj.equals(twoObj);
    }

    /**
     * 根据用户数据激活1个标签
     */
    public static void activeTab(TabPane tabpane, Object userData) {
        if (userData == null) {
            return;
        }

        Tab tab = findTab(tabpane, userData);
        if (tab == null) {
            // 不存在Tab标签
            return;
        }

        if (!tab.isSelected()) {
            // 激活
            tabpane.getSelectionModel().select(tab);
        }
    }

    /**
     * 增加并且激活1个Tab标签
     */
    public static void addAndActiveTab(TabPane tabpane, Tab tab) {
        if (tab == null) {
            return;
        }

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
    public static void addUniqueTab(TabPane tabpane, Tab uniqueTab) {
        if (tabpane == null) {
            return;
        }

        Object userData = findTabUserData(uniqueTab);
        if (userData == null) {
            return;
        }

        Tab existTab = findTab(tabpane, userData);
        if (existTab != null) {
            // 删除标签
            tabpane.getTabs().removeAll(existTab);
        }

        // 增加并激活标签
        addAndActiveTab(tabpane, uniqueTab);
    }

    /**
     * 获取标签用户数据，如果不存在，则返回为NULL对象
     * <p/>
     * 标签用户数据参见{@link com.atom.core.lang.enums.EnumBase}
     */
    @SuppressWarnings("unchecked")
    public static <T> T findTabUserData(Tab tab) {
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
    public static void removeTab(TabPane tabpane, Tab tab) {
        if(tab == null) {
            return;
        }
        
        if (tabpane == null) {
            return;
        }

        tabpane.getTabs().removeAll(tab);
    }

    /**
     * 根据用户数据删除Tab标签
     */
    public static Tab removeTab(TabPane tabpane, Object userData) {
        if(userData == null) {
            return null;
        }

        Tab tab = findTab(tabpane, userData);
        removeTab(tabpane, tab);

        return tab;
    }
    
}
