/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
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

import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.utils.IconsHolder;
import com.atom.core.uijfx.utils.StageUtils;

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
    private boolean      modal         = true;

    @FXML
    private GridPane     rootView;
    private List<Image>  iconImages;

    @FXML
    private ImageView    icon;
    private Image        image;
    private String       title;

    @FXML
    private Label        lblMsg;
    @FXML
    private Label        lblDesp;

    @FXML
    private HBox         hboxButtons;
    private List<Button> buttons;
    private int          btnStyle      = -1;
    private String       btnSureText   = BTN_SURE_TEXT;
    private String       btnCancelText = BTN_CANCEL_TEXT;
    private String       btnHelpText   = BTN_HELP_TEXT;
    private String       helpDocUrl;

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
        // 清空测试文本
        this.lblMsg.setText(StringUtils.EMPTY);
        this.lblDesp.setText(StringUtils.EMPTY);

        // 删除原按钮
        int size = this.hboxButtons.getChildren().size();
        for (int i = 0; i < size; i++) {
            this.hboxButtons.getChildren().remove(0);
        }
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
    public PopupView setImage(int imgValue) {
        if ((IMG_FAILURE & imgValue) == imgValue) {
            this.image = this.findFailureIcon();
        } else if ((IMG_SUCCESS & imgValue) == imgValue) {
            this.image = this.findSuccessIcon();
        } else if ((IMG_CONFIRM & imgValue) == imgValue) {
            this.image = this.findConfirmIcon();
        }

        return this;
    }

    public PopupView setImage(Image img) {
        this.image = img;
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
    public PopupView setDesp(String desp) {
        this.lblDesp.setText(desp);
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
     * 设置‘确定’按钮文本
     */
    public PopupView setSureText(String btnSureText) {
        if (StringUtils.isNotBlank(btnSureText)) {
            this.btnSureText = btnSureText;
        }
        return this;
    }

    /**
     * 设置‘取消’按钮文本
     */
    public PopupView setCancelText(String btnCancelText) {
        if (StringUtils.isNotBlank(btnCancelText)) {
            this.btnCancelText = btnCancelText;
        }
        return this;
    }

    /**
     * 设置‘帮助’按钮文本
     */
    public PopupView setHelpText(String btnHelpText) {
        if (StringUtils.isNotBlank(btnHelpText)) {
            this.btnHelpText = btnHelpText;
        }
        return this;
    }

    /**
     * 设置‘帮助’文档URL
     */
    public PopupView setHelpDocUrl(String helpDocUrl) {
        if (StringUtils.isNotBlank(helpDocUrl)) {
            this.helpDocUrl = helpDocUrl;
        }
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
        if (this.iconImages == null || this.iconImages.isEmpty()) {
            this.iconImages = IconsHolder.getIconImages();
        }

        this.newStage.getIcons().addAll(this.iconImages);

        // 标题
        if (this.title != null) {
            this.newStage.setTitle(this.title);
        } else {
            this.newStage.setTitle(DEFAULT_TITLE);
        }

        // 提示图片
        if (this.image != null) {
            this.icon.setImage(this.image);
        } else {
            // this.icon.setImage(this.findNormalIcon());
            this.icon.setVisible(false);
        }

        // 提示内容
        if (StringUtils.isBlank(this.lblMsg.getText())) {
            this.lblMsg.setVisible(false);
        }
        if (StringUtils.isBlank(this.lblDesp.getText())) {
            this.lblDesp.setVisible(false);
        }

        // 增加新按钮
        if (this.buttons != null && !this.buttons.isEmpty()) {
            this.hboxButtons.getChildren().addAll(this.buttons);
        } else {
            List<Button> buttons = new ArrayList<Button>();

            if (this.btnStyle <= 0) {
                this.btnStyle = BTN_SURE_CANCEL_HELP_VALUE;
            }

            // 选择性增加按钮
            if ((this.btnStyle & BTN_SURE_VALUE) == BTN_SURE_VALUE) {
                // 确定
                this.initSureButton(buttons);
            }

            if ((this.btnStyle & BTN_CANCEL_VALUE) == BTN_CANCEL_VALUE) {
                // 取消
                this.initCancelButton(buttons);
            }

            if ((this.btnStyle & BTN_HELP_VALUE) == BTN_HELP_VALUE && StringUtils.isNotBlank(this.helpDocUrl)) {
                // 帮助
                this.initHelpButton(buttons);
            }

            // 增加新按钮组
            if (!buttons.isEmpty()) {
                this.hboxButtons.getChildren().addAll(buttons);
            }
        }

        this.newStage.setScene(new Scene(this.rootView));
        this.newStage.sizeToScene();
        this.newStage.setResizable(false);
        this.newStage.show();

        // 设置弹出窗口居中其父窗口
        StageUtils.centerNewStage(this.stage, this.newStage);
    }

    /**
     * 初始化按钮
     */
    private Button initButton(String text, final int value) {
        Button button = new Button(text);
        button.setMinWidth(60.0);
        button.setMinHeight(25.0);
        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (callback != null) {
                    callback.callback(newStage, value);
                } else {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            newStage.close();
                        }
                    });
                }
            }
        });

        return button;
    }

    private void initSureButton(List<Button> buttons) {
        buttons.add(this.initButton(this.btnSureText, BTN_SURE_VALUE));
    }

    private void initCancelButton(List<Button> buttons) {
        buttons.add(this.initButton(this.btnCancelText, BTN_CANCEL_VALUE));
    }

    private void initHelpButton(List<Button> buttons) {
        Button btnHelp = this.initButton(this.btnHelpText, BTN_HELP_VALUE);

        if (StringUtils.isNotBlank(this.helpDocUrl)) {
            btnHelp.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String doc = helpDocUrl;
                    try {
                        if (StringUtils.startsWith(doc, "www.")) {
                            doc = "http://" + doc;
                        }

                        if (StringUtils.startsWith(doc, "http")) {
                            Desktop.getDesktop().browse(new URL(doc).toURI());
                        } else {
                            Desktop.getDesktop().open(new File(doc));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("打开帮助文档[" + doc + "]异常！", e);
                    }
                }
            });
        }

        buttons.add(btnHelp);
    }

    /**
     * 成功-图片
     */
    public Image findSuccessIcon() {
        return new Image(getClass().getResourceAsStream("icon-success.gif"), 50, 50, false, false);
    }

    /**
     * 失败-图片
     */
    public Image findFailureIcon() {
        return new Image(getClass().getResourceAsStream("icon-failure.gif"), 50, 50, false, false);
    }

    /**
     * 确认-图片
     */
    public Image findConfirmIcon() {
        return new Image(getClass().getResourceAsStream("icon-confirm.png"), 50, 50, false, false);
    }

}
