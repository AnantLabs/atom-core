/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PopupView测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupViewTest.java, V1.0.1 2013-2-5 上午10:19:57 $
 */
public class PopupViewTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public void start(Stage stage) throws Exception {
        PopupView view = new PopupView(stage);
        
        view.show();
    }

}
