/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.atom.core.lang.utils.LogUtils;

/**
 * 弹出框
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupView.java, V1.0.1 2013-2-3 下午10:28:54 $
 */
public class PopupView implements PopupConst {

    /** 根视图 */
    private Stage        stage;
    private Stage        newStage;
    private boolean      modal    = true; ;

    @FXML
    private GridPane     rootView;
    private List<Image>  iconImages;

    @FXML
    private ImageView    icon;
    private Image        tipImage;
    private String       title;

    @FXML
    private Label        lblMsg;

    @FXML
    private Label        lblContent;

    @FXML
    private HBox         hboxButtons;
    private List<Button> buttons;
    private int          btnStyle = -1;

    private PopupEvent   callback;

    /**
     * 初始化
     */
    public PopupView(Stage stage) {
        this.stage = stage;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PopupView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            LogUtils.error("初始化PopupView组件异常!", e);
            throw new RuntimeException(e);
        }

        this.initViews();
    }

    /**
     * 初始化
     */
    protected void initViews() {
    }

    /**
     * 设置场景
     */
    public PopupView setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    /**
     * 图片窗口Icon信息
     */
    public PopupView setIcons(List<Image> imgs) {
        this.iconImages = imgs;
        return this;
    }

    /**
     * 设置是否为模态
     */
    public PopupView setModal(boolean modal) {
        this.modal = modal;
        return this;
    }

    /**
     * 设置图片信息
     */
    public PopupView setTipIcon(Image img) {
        this.tipImage = img;
        return this;
    }

    /**
     * 设置标题内容
     */
    public PopupView setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置提示信息标题
     */
    public PopupView setMsg(String msg) {
        this.lblMsg.setText(msg);
        return this;
    }

    /**
     * 设置提示信息内容
     */
    public PopupView setContent(String content) {
        this.lblContent.setText(content);
        return this;
    }

    /**
     * 设置按钮
     */
    public PopupView setButtons(List<Button> buttons) {
        this.buttons = buttons;
        return this;
    }

    /**
     * 设置按钮值
     */
    public PopupView setBtnStyle(int btnStyle) {
        this.btnStyle = btnStyle;
        return this;
    }

    /**
     * 设置回调函数
     */
    public PopupView setCallback(PopupEvent callback) {
        this.callback = callback;
        return this;
    }

    /**
     * 展示视图
     */
    public void show() {
        this.newStage = new Stage();
        this.newStage.initOwner(this.stage);
        if (this.modal) {
            this.newStage.initModality(Modality.APPLICATION_MODAL);
        }

        // 窗口Icon
        if (this.iconImages != null && !this.iconImages.isEmpty()) {
            this.newStage.getIcons().addAll(this.iconImages);
        }

        // 标题
        if (this.title != null) {
            this.newStage.setTitle(this.title);
        } else {
            this.newStage.setTitle(DEFAULT_TITLE);
        }

        // 提示图片
        if (this.tipImage != null) {
            this.icon.setImage(this.tipImage);
        } else {
            this.icon.setImage(this.findNormalIcon());
        }

        // 按钮组
        if (this.buttons == null) {
            List<Button> buttons = new ArrayList<Button>();

            if (this.btnStyle <= 0) {
                // 确定
                this.initSureButton(buttons);

                // 取消
                this.initCancelButton(buttons);
            } else {
                // 选择性增加按钮
                if ((this.btnStyle & BTN_SURE_VALUE) == BTN_SURE_VALUE) {
                    // 确定
                    this.initSureButton(buttons);
                }

                if ((this.btnStyle & BTN_CANCEL_VALUE) == BTN_CANCEL_VALUE) {
                    // 取消
                    this.initCancelButton(buttons);
                }
            }

            // 增加新按钮组
            this.hboxButtons.getChildren().addAll(buttons);
        } else {
            // 删除原按钮
            int size = this.hboxButtons.getChildren().size();
            for (int i = 0; i < size; i++) {
                this.hboxButtons.getChildren().remove(i);
            }

            // 增加新按钮组
            this.hboxButtons.getChildren().addAll(this.buttons);
        }

        this.newStage.setScene(new Scene(this.rootView));
        this.newStage.sizeToScene();
        this.newStage.setResizable(false);
        this.newStage.show();
    }

    private Button initButton(String text, final int value) {
        Button sure = new Button(text);
        sure.setMinWidth(60.0);
        sure.setMaxHeight(25.0);
        sure.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (callback != null) {
                    callback.callback(value);
                }

                Platform.exit();
            }
        });

        return sure;
    }

    private void initSureButton(List<Button> buttons) {
        buttons.add(this.initButton(BTN_SURE_TEXT, BTN_SURE_VALUE));
    }

    private void initCancelButton(List<Button> buttons) {
        buttons.add(this.initButton(BTN_CANCEL_TEXT, BTN_CANCEL_VALUE));
    }

    /**
     * 一般-图片
     */
    private Image findNormalIcon() {
        return new Image(getClass().getResourceAsStream("icon-success.gif"));
    }

    /**
     * 成功-图片
     */
    private Image findSuccessIcon() {
        return new Image(getClass().getResourceAsStream("icon-success.gif"));
    }

    /**
     * 失败-图片
     */
    private Image findFailureIcon() {
        return new Image(getClass().getResourceAsStream("icon-failure.gif"));
    }

}
