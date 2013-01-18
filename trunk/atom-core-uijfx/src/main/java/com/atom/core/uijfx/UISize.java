/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * UI尺寸
 * 
 * @author obullxl@gmail.com
 * @version $Id: UISize.java, 2012-8-18 下午8:25:49 Exp $
 */
public class UISize {
    /** 默认尺寸 */
    public static final int D_WIDTH  = 800;
    public static final int D_HEIGHT = 600;

    /** 最佳尺寸 */
    public static final int P_WIDTH  = 1020;
    public static final int P_HEIGHT = 750;

    /** 宽 */
    private double          width;
    /** 高 */
    private double          height;

    private UISize() {
    }

    private UISize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 尺寸
     */
    public static UISize to(double width, double height) {
        return new UISize(width, height);
    }

    /**
     * 最佳尺寸
     */
    public static UISize toPrefSize() {
        return to(P_WIDTH, P_HEIGHT);
    }

    /**
     * 默认尺寸
     */
    public static UISize toDefault() {
        return to(D_WIDTH, D_HEIGHT);
    }

    /**
     * 最大尺寸，屏幕大小的80%
     */
    public static UISize toMaxSize() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        return to(screen.getWidth() * 0.8, screen.getHeight() * 0.8);
    }

    /**
     * 屏幕大小
     */
    public static UISize toScreenSize() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        return to(screen.getWidth(), screen.getHeight());
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[" + this.width + ", " + this.height + "]";
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
