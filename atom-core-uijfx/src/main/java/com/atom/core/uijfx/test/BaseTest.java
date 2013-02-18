/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.test;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 单元测试基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseTest.java, V1.0.1 2013-2-14 下午2:32:20 $
 */
public abstract class BaseTest extends Application {

    /**
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public final void start(final Stage stage) throws Exception {
        Button button = new Button("重新打开新窗口");
        button.setMinHeight(25.0);
        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                onTest(stage);
            }
        });

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        hbox.getChildren().add(button);

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox);

        Group root = new Group();
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 150.0, 50.0);
        vbox.setPrefWidth(scene.getWidth());
        vbox.setPrefHeight(scene.getHeight());

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                vbox.setPrefWidth(newValue.doubleValue());
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                vbox.setPrefHeight(newValue.doubleValue());
            }
        });

        stage.setScene(scene);
        stage.setTitle("单元测试");
        stage.centerOnScreen();
        stage.show();

        this.onTest(stage);
    }

    /**
     * 执行单元测试内容
     */
    public abstract void onTest(Stage stage);

}
