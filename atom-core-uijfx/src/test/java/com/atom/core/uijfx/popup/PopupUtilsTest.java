/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

import javafx.stage.Stage;

import com.atom.core.uijfx.test.BaseTest;

/**
 * PopupUtils测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupUtilsTest.java, V1.0.1 2013-2-5 上午10:36:05 $
 */
public class PopupUtilsTest extends BaseTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /** 
     * @see com.atom.core.uijfx.test.BaseTest#onTest(javafx.stage.Stage)
     */
    public void onTest(Stage stage) {
        // PopupUtils.alert(stage, "出错提示", "输入有误，请检查重新输入");
        // font    : Font.font("宋体", 14)
        PopupUtils.confirm(stage, "出错提示", "输入有误，请检查重新输入", null);
    }

}
