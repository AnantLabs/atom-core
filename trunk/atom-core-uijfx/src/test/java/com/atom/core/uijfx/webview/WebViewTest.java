/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.webview;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.uijfx.utils.WebViewUtils;
import com.atom.core.uijfx.utils.WebViewUtils.WebViewStateEvent;

/**
 * WebView测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebViewTest.java, V1.0.1 2013-2-14 下午2:52:10 $
 */
public class WebViewTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        this.initWebView(border);

        Scene scene = new Scene(border, 600, 500);
        stage.setTitle("WebView单元测试");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void initWebView(BorderPane border) {
        // Center
        String path = CfgUtils.findConfigPath();
        String file = "file:///" + FilenameUtils.normalize(path + "/views/WebTest01.html");
        final WebView view = WebViewUtils.initWebView(file);
        border.setCenter(view);

        // Bottom
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(3, 20, 3, 0));

        final Button btnAdd = ButtonBuilder.create().text("Do Add").minHeight(25).build();
        WebViewUtils.registWebStateSucceedEvent(view, new WebViewStateEvent() {
            public void onWebViewStateChanged(State oldState, State newState) {
                btnAdd.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent evt) {
                        WebViewUtils.executeScript(view, "doAdd()");
                    }
                });

                WebViewUtils.putHtmlWindowObject(view, "app", new WebHtmlEvent());
            }
        });
        hbox.getChildren().add(btnAdd);

        final Button btnMultiply = ButtonBuilder.create().text("Do Multiply").minHeight(25).build();
        WebViewUtils.registWebStateSucceedEvent(view, new WebViewStateEvent() {
            public void onWebViewStateChanged(State oldState, State newState) {
                btnMultiply.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent evt) {
                        int a = RandomUtils.nextInt(100);
                        int b = RandomUtils.nextInt(100);
                        System.out.println("Do multiply(" + a + "," + b + ") = " + (a * b));
                        WebViewUtils.executeScript(view, "multiply(" + a + ", " + b + ")");
                    }
                });
            }
        });
        hbox.getChildren().add(btnMultiply);

        border.setBottom(hbox);
    }

    public static class WebHtmlEvent {

        public void exit() {
            Platform.exit();
        }

        public void doAdd(int a, int b) {
            System.out.println(a + "+" + b + " = " + (a + b));
        }
    }

}
