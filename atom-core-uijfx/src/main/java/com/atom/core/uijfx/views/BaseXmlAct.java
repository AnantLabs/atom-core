/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * FXML视图控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseXmlAct.java, V1.0.1 2013-2-7 下午8:12:39 $
 */
public abstract class BaseXmlAct extends BaseViewAct {

    /**
     * 默认构造器
     * 
     * @param stage 主舞台
     */
    public BaseXmlAct(Stage stage) {
        // 使用基础控制器初始化
        this(stage, null);
    }

    /**
     * 默认构造器
     * 
     * @param stage 主舞台
     * @param rootAct 上级/根节点视图控制器，可以为NULL.
     */
    public BaseXmlAct(Stage stage, URL fxml) {
        // 使用基础控制器初始化
        super(stage);

        // 加载FXML内容
        try {
            if (fxml == null) {
                // 如果没有指定FXML文件，则默认为与控制器同名的.fxml文件
                fxml = getClass().getResource(this.getClass().getSimpleName() + ".fxml");
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxml);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException("初始化[" + this.getClass().getName() + "]FXML控制器异常!", e);
        }

        // 初始化
        this.initXmlViews();
    }

    /**
     * 实现基础视图初始化，直接返回，以名重复初始化
     * @see com.atom.core.uijfx.views.BaseViewAct#initActViews()
     */
    public final BaseXmlAct initActViews() {
        return this;
    }

    /**
     * 初始化FXML视图控制器
     */
    @FXML
    public final void initialize() {
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
    public abstract BaseXmlAct initXmlViews();

}
