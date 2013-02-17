/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Button button = new Button("Button-单元测试按钮！");
        button.setMinHeight(25.0);
        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                onTest(stage);
            }
        });

        Group root = new Group();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 600.0, 500.0);
        stage.setScene(scene);
        stage.setTitle("Stage-单元测试舞台！");
        stage.centerOnScreen();
        stage.show();

        this.onTest(stage);
    }

    /**
     * 执行单元测试内容
     */
    public abstract void onTest(Stage stage);

}
