/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx;

import com.atom.core.uijfx.UISize;
import com.atom.core.uijfx.event.EventAdapter;
import com.atom.core.uijfx.utils.StageHolder;
import javafx.stage.Stage;

/**
 * @author obullxl@gmail.com
 */
public class UIConfig {

    private Stage stage;
    private UISize size;
    private UITipMsg tipMsg;
    private UIBtnMsg btnMsg;
    private EventAdapter adapter;

    public static UIConfig get() {
        return new UIConfig();
    }

    private UIConfig() {
        this.stage = StageHolder.get();
        this.size = UISize.toDefault();
        this.adapter = EventAdapter.get();
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getStage(Stage stage) {
        Stage rtn = this.getStage();
        if (rtn == null) {
            rtn = stage;
        }

        return rtn;
    }

    public UIConfig setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public UISize getSize() {
        return size;
    }

    public UISize getSize(UISize size) {
        UISize rtn = this.getSize();
        if(rtn == null) {
            rtn = size;
        }

        return rtn;
    }

    public UIConfig setSize(UISize size) {
        this.size = size;
        return this;
    }

    public UITipMsg getTipMsg() {
        return this.tipMsg;
    }

    public UITipMsg getTipMsg(UITipMsg tipMsg) {
        UITipMsg rtn = this.getTipMsg();
        if(rtn == null) {
            rtn =  tipMsg;
        }

        return rtn;
    }

    public UIConfig setTipMsg(UITipMsg tipMsg) {
        this.tipMsg = tipMsg;
        return this;
    }

    public UIBtnMsg getBtnMsg() {
        return btnMsg;
    }

    public UIBtnMsg getBtnMsg(UIBtnMsg btnMsg) {
        UIBtnMsg rtn = this.getBtnMsg();
        if(rtn == null) {
            rtn = UIBtnMsg.get();
        }

        return rtn;
    }

    public UIConfig setBtnMsg(UIBtnMsg btnMsg) {
        this.btnMsg = btnMsg;
        return this;
    }

    public EventAdapter getAdapter() {
        return adapter;
    }

    public EventAdapter getAdapter(EventAdapter adapter) {
        EventAdapter rtn = this.getAdapter();
        if(rtn == null) {
            rtn = adapter;
        }

        return rtn;
    }

    public UIConfig setAdapter(EventAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
