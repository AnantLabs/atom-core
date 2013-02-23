/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

/**
 * FXML文件工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: FXMLUtils.java, V1.0.1 2013-2-23 下午2:17:37 $
 */
public final class FXMLUtils {
    /** FXML相对路径 */
    private static String FXML_RELATIVE_PATH = "../fxmls/";

    /**
     * 设置FXML文件相对路径
     * <p/>
     * 设置之后，FXML文件URL变成了“getClass().getResource("../fxmls/" + this.getClass().getSimpleName() + ".fxml");”<br/>
     * 其中，“"../fxmls/"”即为FXML文件相对路径。
     */
    public static final void setRelativePathFXML(String path) {
        FXML_RELATIVE_PATH = path;
    }

    /**
     * 获取FXML文件相对路径
     * <p/>
     * FXML文件URL为“getClass().getResource("../fxmls/" + this.getClass().getSimpleName() + ".fxml");”<br/>
     * 其中，“"../fxmls/"”即为FXML文件相对路径。
     */
    public static final String findRelativePathFXML() {
        return FXML_RELATIVE_PATH;
    }

}
