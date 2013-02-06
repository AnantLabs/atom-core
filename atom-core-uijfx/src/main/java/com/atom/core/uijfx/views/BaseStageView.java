/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.atom.core.lang.utils.LogUtils;

/**
 * 主场景UI组件，扩展并增加了通用方法。
 * <p/>
 * 一般情况下，由控制器Controller继承该类！
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseView.java, 2012-8-18 下午8:25:49 Exp $
 */
public abstract class BaseStageView {
    /** 初始化标志 */
    private final AtomicBoolean init = new AtomicBoolean(false);

    /** 主窗体 */
    private Stage               stage;

    /** 主场景 */
    private Scene               scene;

    /**
     * CTOR
     */
    public BaseStageView() {
    }

    public BaseStageView(Stage stage) {
        this();

        this.stage = stage;
    }

    /**
     * 窗体尺寸
     */
    public abstract Dimension2D findSize();

    /**
     * 窗体最小尺寸
     */
    public Dimension2D findMinSize() {
        return new Dimension2D(0.0, 0.0);
    }

    /**
     * 窗体最大尺寸
     */
    public Dimension2D findMaxSize() {
        return new Dimension2D(0.0, 0.0);
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
    public abstract <T extends Parent> T findGroupView();

    /**
     * 设置主场景
     */
    public BaseStageView setStage(Stage stage) {
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
    public BaseStageView initViews(Stage stage) {
        // 参数检查
        if (stage == null) {
            String msg = "初始化视图[" + this.getClass().getName() + "]失败，主场景Stage为NULL！";
            LogUtils.error(msg, new RuntimeException());
            throw new RuntimeException(msg);
        }

        // 初始化控制
        if (this.init.get()) {
            String msg = "视图[" + this.getClass().getName() + "]已经被初始化！";
            LogUtils.error(msg, new RuntimeException());
            throw new RuntimeException(msg);
        }

        // 初始化
        this.init.set(true);

        // 主场景
        this.stage = stage;

        return this;
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

        this.scene = new Scene(this.findGroupView(), this.findSize().getWidth(), this.findSize().getHeight());
        this.stage.setScene(this.scene);

        this.stage.setTitle(this.findTitle());

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

}
