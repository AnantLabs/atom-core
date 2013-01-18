/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx;

/**
 * @author obullxl@gmail.com
 */
public class UITipMsg {

    private final String title;
    private final String message;

    public static final UITipMsg to() {
        return new UITipMsg(null, null);
    }

    public static final UITipMsg to(String title) {
        return new UITipMsg(title, null);
    }

    public static final UITipMsg to(String title, String message) {
        return new UITipMsg(title, message);
    }

    private UITipMsg() {
        this(null);
    }

    private UITipMsg(String title) {
        this(title, null);
    }

    private UITipMsg(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
