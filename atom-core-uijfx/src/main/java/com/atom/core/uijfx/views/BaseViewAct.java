/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.atom.core.uijfx.utils.IconsHolder;
import com.atom.core.uijfx.utils.StageUtils;

/**
 * 基础视图控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseViewAct.java, V1.0.1 2013-2-7 下午7:58:12 $
 */
public abstract class BaseViewAct {
    /** 主舞台，一般为根舞台 */
    private final Stage                       stage;

    /** 新舞台，一般为弹出窗口 */
    private Stage                             newStage;

    /** 上级/根节点视图控制器，可能为NULL引用 */
    private Object                            rootAct;

    /** 根视图，在初始化时必须设值 */
    private final ObjectProperty<Parent>      groupView      = new SimpleObjectProperty<Parent>();

    /** 舞台标题 */
    private final StringProperty              title          = new SimpleStringProperty();

    /** 窗口缩放属性 */
    private final BooleanProperty             resizable      = new SimpleBooleanProperty(false);

    /** 舞台全屏属性 */
    private final BooleanProperty             fullScreen     = new SimpleBooleanProperty(false);

    /** 舞台紧缩属性 */
    private final BooleanProperty             sizeToScene    = new SimpleBooleanProperty(false);

    /** 舞台于屏幕之中属性 */
    private final BooleanProperty             centerOnScreen = new SimpleBooleanProperty(true);

    /** 新舞台居于父舞台之中属性 */
    private final BooleanProperty             centerNewStage = new SimpleBooleanProperty(true);

    /** 新舞台模态属性 */
    private final ObjectProperty<Modality>    modality       = new SimpleObjectProperty<Modality>(Modality.APPLICATION_MODAL);

    /** 场景尺寸属性 */
    private final ObjectProperty<Dimension2D> size           = new SimpleObjectProperty<Dimension2D>();

    /** 场景大小 */
    private final ObjectProperty<Dimension2D> minSize        = new SimpleObjectProperty<Dimension2D>();

    /** 场景大小 */
    private final ObjectProperty<Dimension2D> maxSize        = new SimpleObjectProperty<Dimension2D>();

    /** 样式表 */
    private final ListProperty<String>        styleSheets    = new SimpleListProperty<String>();

    /**
     * 默认构造器
     * 
     * @param stage 主舞台
     */
    public BaseViewAct(Stage stage) {
        if (stage == null) {
            throw new RuntimeException("初始化视图控制器[" + this.getClass().getName() + "]失败，舞台Stage为NULL！");
        }

        this.stage = stage;

        // 初始化
        this.initActViews();
    }

    /**
     * 初始化视图，该方法在创建视图控制器时自动调用，业务逻辑中无需显式调用该方法进行视图初始化！
     * <p/>
     * 所有父类必须通过该方法完成以下设置：<br/>
     * 1、通过findSizeProperty().set(Dimension2D)设置场景（非舞台）大小；<br/>
     * 2、通过findTitleProperty().set(String)设置舞台标题；<br/>
     * 3、通过findGroupViewProperty().set(Parent)设置视图根节点；<br/>
     * <p/>
     * 若是弹出窗口，则必须指定新窗口舞台：setNewStage(new Stage())设置弹出窗口舞台！
     */
    public abstract BaseViewAct initActViews();

    /**
     * 获取主Stage舞台
     */
    public final Stage findStage() {
        return this.stage;
    }

    /**
     * 获取新Stage舞台，可能为NULL对象
     */
    public final Stage findNewStage() {
        return this.newStage;
    }

    /**
     * 获取最新舞台
     */
    public final Stage findTopStage() {
        if (this.newStage != null) {
            return this.newStage;
        }

        return this.stage;
    }

    /**
     * 设置新Stage舞台
     */
    public final BaseViewAct setNewStage(Stage newStage) {
        this.newStage = newStage;
        return this;
    }

    /**
     * 关闭主Stage舞台
     */
    public final BaseViewAct closeStage() {
        this.closeStage(this.findStage());
        return this;
    }

    /**
     * 关闭新Stage舞台
     */
    public final BaseViewAct closeNewStage() {
        this.closeStage(this.findNewStage());
        return this;
    }

    /**
     * 关闭指定的Stage舞台
     */
    public final void closeStage(final Stage stage) {
        if (stage != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    stage.close();
                }
            });
        }
    }

    /**
     * 设置父View控制器
     */
    public final BaseViewAct setRootAct(Object rootAct) {
        this.rootAct = rootAct;
        return this;
    }

    /**
     * 获取父View控制器
     */
    @SuppressWarnings("unchecked")
    public final <T> T findRootAct() {
        return (T) this.rootAct;
    }

    /**
     * 获取组窗体。
     * <p/>
     * 该内容作为主场景根节点，一般为Pane，如BorderPane，AnchorPane等等。<br/>
     * 该对象一般为最顶层视图构建。
     */
    public final ObjectProperty<Parent> findGroupViewProperty() {
        return this.groupView;
    }

    /**
     * 样式列表
     */
    public final ListProperty<String> findStyleSheetsProperty() {
        return this.styleSheets;
    }

    /**
     * 弹出窗口Stage模态属性
     */
    public final ObjectProperty<Modality> findModalityProperty() {
        return this.modality;
    }

    /**
     * 窗体尺寸属性
     */
    public final ObjectProperty<Dimension2D> findSizeProperty() {
        return this.size;
    }

    /**
     * 窗体最小尺寸属性
     */
    public final ObjectProperty<Dimension2D> findMinSizeProperty() {
        return this.minSize;
    }

    /**
     * 窗体最大尺寸属性
     */
    public final ObjectProperty<Dimension2D> findMaxSizeProperty() {
        return this.maxSize;
    }

    /**
     * 窗体标题属性
     */
    public final StringProperty findTitleProperty() {
        return this.title;
    }

    /**
     * 舞台绽放属性
     */
    public final BooleanProperty findResizableProperty() {
        return this.resizable;
    }

    /**
     * 舞台全屏属性
     */
    public final BooleanProperty findFullScreenProperty() {
        return this.fullScreen;
    }

    /**
     * 舞台紧缩属性
     */
    public final BooleanProperty findSizeToSceneProperty() {
        return this.sizeToScene;
    }

    /**
     * 舞台居于屏幕之中属性
     */
    public final BooleanProperty findCenterOnScreenProperty() {
        return this.centerOnScreen;
    }

    /**
     * 新舞台居于父舞台之中属性
     */
    public final BooleanProperty findCenterNewStageProperty() {
        return this.centerNewStage;
    }

    /**
     * 展示窗体
     */
    public BaseViewAct show() {
        if (this.newStage == null) {
            this.showStage(this.stage);
        } else {
            this.newStage.initOwner(this.stage);

            Modality modal = this.findModalityProperty().get();
            if (modal != null) {
                this.newStage.initModality(modal);
            }
            this.newStage.getIcons().addAll(IconsHolder.getIconImages());

            this.showStage(this.newStage);

            // 设置弹出窗口居中其父窗口
            if (this.findCenterNewStageProperty().get()) {
                StageUtils.centerNewStage(this.stage, this.newStage);
            }
        }

        // 初始化组件事件
        this.initViewEvent();

        // 初始化完成动作
        this.initViewShown();

        return this;
    }

    /**
     * 初始化组件参数事件
     */
    private final void initViewEvent() {
        // 窗口标题更改事件
        this.findTitleProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> prop, String oldValue, String newValue) {
                findTopStage().setTitle(newValue);
            }
        });
    }

    /**
     * 钩子方法，初始化完成功能动作，主要对舞台或场景整体增加功能，如场景中增加方向功能键。
     */
    public void initViewShown() {
    }

    /**
     * 展示视图
     */
    private final void showStage(Stage stage) {
        // 标题
        stage.setTitle(this.findTitleProperty().get());

        // 场景
        Dimension2D size = this.findSizeProperty().get();
        if (size == null) {
            throw new RuntimeException("舞台场景大小为NULL，请通过#findSizeProperty().set(Dimension2D)进行设置！");
        }

        Parent rootView = this.findGroupViewProperty().get();
        if (rootView == null) {
            throw new RuntimeException("舞台场景根视图为NULL，请通过#findGroupViewProperty().set(Parent)进行设置！");
        }

        Scene scene = new Scene(rootView, size.getWidth(), size.getHeight());

        List<String> styleSheets = this.findStyleSheetsProperty().get();
        if (styleSheets != null && !styleSheets.isEmpty()) {
            scene.getStylesheets().addAll(styleSheets);
        }

        stage.setScene(scene);

        // 压缩
        if (this.findSizeToSceneProperty().get()) {
            stage.sizeToScene();
        }

        if (this.findFullScreenProperty().get()) {
            // 全屏设置
            stage.setX(-100000);
            stage.setY(-100000);
            stage.setFullScreen(true);
        } else {
            // 非全屏设置
            Dimension2D minSize = this.findMinSizeProperty().get();
            if ((minSize != null) && (minSize.getWidth() + minSize.getHeight() > 1.0D)) {
                stage.setMinWidth(minSize.getWidth());
                stage.setMinHeight(minSize.getHeight());
            }

            Dimension2D maxSize = this.findMaxSizeProperty().get();
            if ((maxSize != null) && (maxSize.getWidth() + maxSize.getHeight() > 1.0D)) {
                stage.setMaxWidth(maxSize.getWidth());
                stage.setMaxHeight(maxSize.getHeight());
            }

            // 缩放
            stage.setResizable(this.findResizableProperty().get());

            // 居中
            if (this.findCenterOnScreenProperty().get()) {
                stage.centerOnScreen();
            }
        }

        // 展示
        stage.show();
    }

}
