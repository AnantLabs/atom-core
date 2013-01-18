/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx;

/**
 * @author obullxl@gmail.com
 */
public class UIBtnMsg {

    private String sure;
    private String cancel;

    public static UIBtnMsg get() {
        return new UIBtnMsg();
    }

    private UIBtnMsg() {
    }

    public String getSure() {
        return sure;
    }

    public UIBtnMsg setSure(String sure) {
        this.sure = sure;

        return this;
    }

    public String getCancel() {
        return cancel;
    }

    public UIBtnMsg setCancel(String cancel) {
        this.cancel = cancel;

        return this;
    }
}
