/**
 * obullxl@gmail.com
 */
package com.atom.core.uijfx.utils;

import javafx.stage.Stage;

/**
 * Stage线程持有类
 * 
 * @author obullxl@gmail.com
 * @version $Id: StageHolder.java, 2012-8-18 下午8:25:49 Exp $
 */
public class StageHolder {
    private static final ThreadLocal<Stage> _holder = new ThreadLocal<Stage>();

    /**
     * 设置
     */
    public static void set(Stage stage) {
        _holder.set(stage);
    }

    /**
     * 清理
     */
    public static void remove() {
        _holder.remove();
    }

    /**
     * 获取
     */
    public static Stage get() {
        return _holder.get();
    }

}
