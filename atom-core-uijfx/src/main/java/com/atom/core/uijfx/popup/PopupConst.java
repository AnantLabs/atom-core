/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.popup;

/**
 * 常量
 * 
 * @author obullxl@gmail.com
 * @version $Id: PopupConst.java, V1.0.1 2013-2-4 下午10:32:07 $
 */
public interface PopupConst {

    /** 默认标题 */
    public static final String DEFAULT_TITLE              = "提示对话窗口";

    /** 图片-失败 */
    public static final int    IMG_FAILURE                = 1;

    /** 图片-成功 */
    public static final int    IMG_SUCCESS                = 2;

    /** 图片-确认 */
    public static final int    IMG_CONFIRM                = 4;

    /** 确定 */
    public static final int    BTN_SURE_VALUE             = 1;

    public static final String BTN_SURE_TEXT              = "确定";

    /** 取消 */
    public static final int    BTN_CANCEL_VALUE           = 2;

    public static final String BTN_CANCEL_TEXT            = "取消";

    /** 帮助 */
    public static final int    BTN_HELP_VALUE             = 4;

    public static final String BTN_HELP_TEXT              = "帮助";

    /** 确定-取消 */
    public static final int    BTN_SURE_CANCEL_VALUE      = (BTN_SURE_VALUE | BTN_CANCEL_VALUE);

    /** 确定-取消-帮助 */
    public static final int    BTN_SURE_CANCEL_HELP_VALUE = (BTN_SURE_CANCEL_VALUE | BTN_HELP_VALUE);

}
