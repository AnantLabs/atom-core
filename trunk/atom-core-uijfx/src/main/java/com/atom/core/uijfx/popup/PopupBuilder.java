/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Builder;

import com.atom.core.uijfx.utils.StageHolder;

/**
 * Popup构建起
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupBuilder.java, V1.0.1 2013-3-25 下午9:13:04 $
 */
public final class PopupBuilder implements Builder<PopupView> {
    /** 弹出窗体 */
    private final PopupView view;

    /**
     * 创建构建器
     */
    public final static PopupBuilder create() {
        return create(StageHolder.get());
    }
    
    /**
     * 创建构建器
     */
    public final static PopupBuilder create(final Stage stage) {
        return new PopupBuilder(stage);
    }
    
    /**
     * 构造器
     */
    private PopupBuilder(final Stage stage) {
        this.view = new PopupView(stage);
    }

    /** 
     * @see javafx.util.Builder#build()
     */
    public PopupView build() {
        return this.view;
    }
    
    /**
     * 设置模态
     */
    public PopupBuilder modal(boolean modal) {
        this.view.setModal(modal);
        return this;
    }
    
    /**
     * 设置图片
     */
    public PopupBuilder image(Image img) {
        this.view.setImage(img);
        return this;
    }
    
    /**
     * 设置图片ID值
     */
    public PopupBuilder imageValue(int imgValue) {
        this.view.setImage(imgValue);
        return this;
    }
    
    /**
     * 设置标题
     */
    public PopupBuilder title(String title) {
        this.view.setTitle(title);
        return this;
    }
    
    /**
     * 设置提示信息内容
     */
    public PopupBuilder despMsg(String desp) {
        this.view.setDesp(desp);
        return this;
    }

    /**
     * 设置按钮
     */
    public PopupBuilder buttons(int btnValue) {
        this.view.setBtnStyle(btnValue);
        return this;
    }
    
    /**
     * 设置按钮ID值
     */
    public PopupBuilder buttonsValue(List<Button> buttons) {
        this.view.setButtons(buttons);
        return this;
    }
    
    /**
     * 设置回调函数
     */
    public PopupBuilder callback(PopupEvent callback) {
        this.view.setCallback(callback);
        return this;
    }
    
}
