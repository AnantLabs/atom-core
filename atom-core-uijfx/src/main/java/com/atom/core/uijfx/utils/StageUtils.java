/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.uijfx.utils;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import org.apache.commons.lang.StringUtils;

/**
 * Stage工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: StageUtils.java, 2012-8-18 下午8:25:49 Exp $
 */
public class StageUtils {

    /**
     * 根据Controller类获取FXML文件名
     */
    public static String findFXMLName(Class<?> controller) {
        return StringUtils.substringBefore(controller.getSimpleName(), "Controller");
    }

    /**
     * 根据Controller类获取Controller对象
     */
    public static <T> T findController(Class<T> clazz) {
        return findController(clazz, findFXMLName(clazz) + ".fxml");
    }

    /**
     * 根据Controller类和FXML名获取Controller对象
     */
    public static <T> T findController(Class<T> clazz, String fxml) {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(clazz.getResource(fxml));

        try {
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException("获取Controller异常, Clazz[" + clazz.getClass() + "], FXML[" + fxml + "].", e);
        }

        return loader.getController();
    }

    /**
     * 取得主屏幕尺寸
     */
    public static Rectangle2D findScreenSize() {
        return Screen.getPrimary().getVisualBounds();
    }
}
