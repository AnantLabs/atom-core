/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.apache.commons.lang.StringUtils;

/**
 * WebView视图基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseWebView.java, V1.0.1 2013-2-6 下午12:57:15 $
 */
public abstract class BaseWebView extends BaseView {
    /** Web组件 */
    private final WebView   webView;
    private final WebEngine webEngine;

    /**
     * CTOR
     */
    public BaseWebView() {
        this.webView = new WebView();
        this.webEngine = this.webView.getEngine();

        this.webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.FAILED) {
                    onWebLoadFailed();
                } else if (newState == State.SUCCEEDED) {
                    onWebLoadSucceeded();
                }
            }
        });
    }

    public BaseWebView(String url) {
        this();

        this.webEngine.load(url);
    }

    /**
     * 网页加载失败事件
     */
    public void onWebLoadFailed() {
    }

    /**
     * 网页加载成功事件
     */
    public void onWebLoadSucceeded() {
    }
    
    /**
     * 取得WebView对象
     */
    public final WebView findWebView() {
        return this.webView;
    }
    
    /**
     * 取得WebEngine对象
     */
    public final WebEngine findWebEngine() {
        return this.webEngine;
    }
    
    /**
     * 停止Vedio视频
     */
    public void stopVedio(String vedioId) {
        String script = "document.getElementById('$ID$').currentTime=0; document.getElementById('$ID$').pause();";
        script = StringUtils.replace(script, "$ID$", vedioId);
        
        this.webEngine.executeScript(script);
    }

    /**
     * 停止Audio音频
     */
    public void stopAudio(String audioId) {
    }

}
