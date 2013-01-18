/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.event;

import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

/**
 * @author obullxl@gmail.com
 */
public class EventAdapter {
    /** 默认 */
    private static final EventAdapter DEFAULT = new EventAdapter();

    /**
     * 获取默认事件适配器
     */
    public static EventAdapter get() {
        return DEFAULT;
    }
    
    /**
     * 窗口关闭事件
     */
    public void onHidden(WindowEvent evt) {
    }

    /**
     * ‘确定’按钮事件
     */
    public void onSure(ActionEvent evt) {
    }
    
    /**
     * ‘取消’按钮事件
     */
    public void onCancel(ActionEvent evt) {
    }

}
