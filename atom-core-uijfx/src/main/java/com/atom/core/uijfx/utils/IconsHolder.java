/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * 应用IconImage持有器，应用系统在启动时设置值，后续从该持有器中获取。
 * 
 * @author obullxl@gmail.com
 * @version $Id: IconsHolder.java, V1.0.1 2013-2-6 下午12:54:19 $
 */
public class IconsHolder {
    /** 图片列表 */
    private static final List<Image> _icons = new ArrayList<Image>();

    /**
     * 设置图片列表
     */
    public static void setIconImages(List<Image> images) {
        if (images == null || images.isEmpty()) {
            return;
        }

        _icons.clear();
        _icons.addAll(images);
    }
    
    /**
     * 增加一张图片
     */
    public static void putIconImage(Image image) {
        if (image == null) {
            return;
        }

        _icons.add(image);
    }

    /**
     * 获取图片列表
     */
    public static List<Image> getIconImages() {
        return new ArrayList<Image>(_icons);
    }
    
}
