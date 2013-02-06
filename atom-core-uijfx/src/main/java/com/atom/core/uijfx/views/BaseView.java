/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import java.util.concurrent.atomic.AtomicBoolean;

import com.atom.core.lang.utils.LogUtils;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * UI组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseView.java, 2012-8-18 下午8:25:49 Exp $
 */
public abstract class BaseView {
    /** 初始化标志 */
    private final AtomicBoolean init = new AtomicBoolean(false);

    /** 主窗体 */
    private Stage               stage;

    /** 主场景 */
    private final Scene         scene;

    /** 组件组 */
    private final Group         group;

    /**
     * CTOR
     */
    public BaseView() {
        this.group = new Group();
        this.scene = new Scene(this.group);
    }

    public BaseView(Stage stage) {
        this();

        this.stage = stage;
    }

    /**
     * 窗体尺寸
     */
    public Dimension2D findSize() {
        return new Dimension2D(600.0, 500.0);
    }

    /**
     * 窗体最小尺寸
     */
    public Dimension2D findMinSize() {
        return new Dimension2D(400.0, 300.0);
    }

    /**
     * 窗体最大尺寸
     */
    public Dimension2D findMaxSize() {
        return new Dimension2D(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * 窗体是否可以改变大小
     */
    public boolean isResizable() {
        return false;
    }

    /**
     * 适应窗口大小
     */
    public boolean isSizeToScene() {
        return false;
    }

    /**
     * 窗口居中
     */
    public boolean isCenterOnScreen() {
        return true;
    }

    /**
     * 窗体标题
     */
    public abstract String findTitle();

    /**
     * 获取组窗体。
     * <p/>
     * 该内容作为主场景根节点，一般为Pane，如BorderPane，AnchorPane等等。<br/>
     * 该对象一般为最顶层视图构建。
     */
    public abstract <T extends Node> T findGroupView();

    /**
     * 设置主场景
     */
    public BaseView setStage(Stage stage) {
        // 参数检查
        if (stage == null) {
            String msg = "初始化视图[" + this.getClass().getName() + "]失败，主场景Stage为NULL！";
            LogUtils.error(msg, new RuntimeException());
            throw new RuntimeException(msg);
        }

        this.stage = stage;
        return this;
    }

    /**
     * 初始化窗体
     */
    public BaseView initViews() {
        // 初始化控制
        if (this.init.get()) {
            String msg = "视图[" + this.getClass().getName() + "]已经被初始化！";
            LogUtils.error(msg, new RuntimeException());
            throw new RuntimeException(msg);
        }

        // 初始化
        this.init.set(true);

        return this;
    }

    /**
     * 初始化窗体
     */
    public BaseView initViews(Stage stage) {
        // 参数检查
        if (stage == null) {
            String msg = "初始化视图[" + this.getClass().getName() + "]失败，主场景Stage为NULL！";
            LogUtils.error(msg, new RuntimeException());
            throw new RuntimeException(msg);
        }

        // 主场景
        this.stage = stage;

        return initViews();
    }

    /**
     * 展示窗体
     */
    public void show() {
        if (this.stage == null || !this.init.get()) {
            String msg = "视图[" + this.getClass().getName() + "]未初始化，请通过#initViews(Stage)方法进行初始化！";
            LogUtils.error(msg, new RuntimeException());
            throw new RuntimeException(msg);
        }

        this.group.getChildren().add(this.findGroupView());
        this.stage.setScene(this.scene);

        this.stage.setTitle(this.findTitle());

        this.stage.setWidth(this.findSize().getWidth());
        this.stage.setHeight(this.findSize().getHeight());

        if (this.findMinSize().getWidth() + this.findMinSize().getHeight() > 1.0D) {
            this.stage.setMinWidth(this.findMinSize().getWidth());
            this.stage.setMinHeight(this.findMinSize().getHeight());
        }

        if (this.findMaxSize().getWidth() + this.findMaxSize().getHeight() > 1.0D) {
            this.stage.setMaxWidth(this.findMaxSize().getWidth());
            this.stage.setMaxHeight(this.findMaxSize().getHeight());
        }

        this.stage.setResizable(this.isResizable());
        
        if (this.isSizeToScene()) {
            this.stage.sizeToScene();
        }

        if (this.isCenterOnScreen()) {
            this.stage.centerOnScreen();
        }

        // 展示
        this.stage.show();
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~ //

    public final Stage findStage() {
        return this.stage;
    }

    public final Scene findScene() {
        return this.scene;
    }

    public final Group findGroup() {
        return this.group;
    }

}
