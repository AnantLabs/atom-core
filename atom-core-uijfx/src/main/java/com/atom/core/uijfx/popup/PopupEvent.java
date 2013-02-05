/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import javafx.stage.Stage;

/**
 * 弹出窗口事件
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupEvent.java, V1.0.1 2013-2-4 下午11:05:40 $
 */
public interface PopupEvent {

    /**
     * 回调函数
     */
    public void callback(Stage newStage, int btnValue);
    
}
