/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PopupUtils测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupUtilsTest.java, V1.0.1 2013-2-5 上午10:36:05 $
 */
public class PopupUtilsTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        PopupUtils.alert(stage, "出错提示", "输入有误，请检查重新输入");
    }

}
