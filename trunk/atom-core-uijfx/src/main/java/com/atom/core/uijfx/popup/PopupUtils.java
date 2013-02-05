/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import javafx.stage.Stage;

/**
 * Popup工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupUtils.java, V1.0.1 2013-2-5 上午10:29:44 $
 */
public final class PopupUtils {

    /**
     * 弹出警告窗口
     * 
     * @param stage 应用主场景
     * @param title 窗口标题
     * @param desp  提示内容
     */
    public static void alert(Stage stage, String title, String desp) {
        PopupView view = new PopupView(stage).setModal(true);
        view.setTitle(title).setImage(PopupConst.IMG_FAILURE).setDesp(desp);

        view.show();
    }

    /**
     * 弹出失败窗口
     * 
     * @param stage 应用主场景
     * @param title 窗口标题
     * @param desp  提示内容
     */
    public static void failure(Stage stage, String title, String desp) {
        failure(stage, title, desp, null);
    }
    
    /**
     * 弹出失败窗口
     * 
     * @param stage 应用主场景
     * @param title 窗口标题
     * @param desp  提示内容
     * @param helpUrl 帮助信息
     */
    public static void failure(Stage stage, String title, String desp, String helpUrl) {
        PopupView view = new PopupView(stage).setModal(true);
        view.setTitle(title).setImage(PopupConst.IMG_FAILURE).setDesp(desp).setHelpDocUrl(helpUrl);

        view.show();
    }
    
    /**
     * 弹出成功窗口
     * 
     * @param stage 应用主场景
     * @param title 窗口标题
     * @param desp  提示内容
     * @param helpUrl 帮助信息
     */
    public static void success(Stage stage, String title, String desp) {
        PopupView view = new PopupView(stage).setModal(true);
        view.setTitle(title).setImage(PopupConst.IMG_SUCCESS).setDesp(desp);

        view.show();
    }

}
