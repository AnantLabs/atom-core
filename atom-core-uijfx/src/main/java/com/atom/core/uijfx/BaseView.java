/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * UI组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseView.java, 2012-8-18 下午8:25:49 Exp $
 */
public abstract class BaseView {

    /** 主要窗体 */
    protected final Stage primaryStage;
    
    /** 主要场景 */
    protected final Scene primaryScene;
    
    /** 主要组件组 */
    protected final Group primaryGroup;

    /**
     * CTOR
     */
    protected BaseView(final Stage stage) {
        this.primaryStage = stage;
        this.primaryGroup = new Group();
        this.primaryScene = new Scene(this.primaryGroup);
    }

    /**
     * 窗体大小
     */
    public abstract UISize findSize();

    /**
     * 窗体是否可以改变大小
     */
    protected boolean isResizable() {
        return false;
    }

    /**
     * 窗体标题
     */
    protected abstract String findTitle();
    
    /**
     * 初始化窗体
     */
    protected abstract <T extends Parent> T initViews();
    
    /**
     * 展示窗体
     */
    protected void show() {
        this.primaryGroup.getChildren().add(this.initViews());
        this.primaryStage.setScene(this.primaryScene);
        
        this.primaryStage.setTitle(this.findTitle());
        this.primaryStage.setWidth(this.findSize().getWidth());
        this.primaryStage.setHeight(this.findSize().getHeight());
        this.primaryStage.setResizable(this.isResizable());
        
        this.primaryStage.sizeToScene();
        this.primaryStage.centerOnScreen();
        
        this.primaryStage.show();
    }
    
}
