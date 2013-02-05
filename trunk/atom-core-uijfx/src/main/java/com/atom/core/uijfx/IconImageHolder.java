/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * 应用IconImage持有器，应用启动时设置
 * 
 * @author obullxl@gmail.com
 * @version $Id: IconImageHolder.java, V1.0.1 2013-2-5 下午12:18:14 $
 */
public final class IconImageHolder {
    /** 图片列表 */
    private static final List<Image> icons = new ArrayList<Image>();

    /**
     * 设置图片列表
     */
    public static void setIconImages(List<Image> images) {
        if (images == null || images.isEmpty()) {
            return;
        }

        icons.clear();
        icons.addAll(images);
    }
    
    /**
     * 增加一张图片
     */
    public static void putIconImage(Image image) {
        if (image == null) {
            return;
        }

        icons.add(image);
    }

    /**
     * 获取图片列表
     */
    public static List<Image> getIconImages() {
        return new ArrayList<Image>(icons);
    }

}
