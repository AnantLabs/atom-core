/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import javafx.application.Platform;
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
     * @param desp  提示内容
     */
    public static void alert(Stage stage, String desp) {
        alert(stage, "操作提示", desp);
    }
    
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
     * @param desp  提示内容
     */
    public static void success(Stage stage, String desp) {
        success(stage, "成功提示", desp);
    }
    
    /**
     * 弹出成功窗口
     * 
     * @param stage 应用主场景
     * @param title 窗口标题
     * @param desp  提示内容
     */
    public static void success(Stage stage, String title, String desp) {
        PopupView view = new PopupView(stage).setModal(true);
        view.setTitle(title).setImage(PopupConst.IMG_SUCCESS).setDesp(desp);

        view.show();
    }
    
    /**
     * 弹出确认窗口
     * 
     * @param stage 应用主场景
     * @param title 窗口标题
     * @param desp  提示内容
     * @param callback 回调函数
     */
    public static void confirm(Stage stage, String title, String desp, PopupEvent callback) {
        PopupView view = new PopupView(stage).setModal(true);
        view.setTitle(title).setDesp(desp);
        view.setImage(PopupConst.IMG_CONFIRM).setCallback(callback);

        view.show();
    }
    
    /**
     * 弹出‘退出系统’确认窗口
     * 
     * @param stage 应用主场景
     */
    public static void exitSystem(Stage stage) {
        exitSystem(stage, "退出系统", "你确定要退出系统吗？");
    }
    
    /**
     * 弹出‘退出系统’确认窗口
     * 
     * @param stage 应用主场景
     * @param title 窗口标题
     * @param desp  提示内容
     */
    public static void exitSystem(Stage stage, String title, String desp) {
        PopupEvent callback = new PopupEvent() {
            public void callback(final Stage newStage, final int btnValue) {
                if (PopupConst.BTN_SURE_VALUE == btnValue) {
                    Platform.exit();
                } else {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            newStage.close();
                        }
                    });
                }
            }
        };

        PopupUtils.confirm(stage, title, desp, callback);
    }
    

}
