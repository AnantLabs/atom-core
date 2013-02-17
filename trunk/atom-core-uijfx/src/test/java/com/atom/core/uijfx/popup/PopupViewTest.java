/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import javafx.stage.Stage;

import com.atom.core.uijfx.test.BaseTest;

/**
 * PopupView测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupViewTest.java, V1.0.1 2013-2-5 上午10:19:57 $
 */
public class PopupViewTest extends BaseTest {

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @see com.atom.core.uijfx.test.BaseTest#onTest(javafx.stage.Stage)
     */
    public void onTest(Stage stage) {
        PopupView view = new PopupView(stage);
        
        view.show();
    }

}
