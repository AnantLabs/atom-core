/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
     * 根据ID获取节点信息，可能返回NULL对象！
     */
    public final Element findElementByID(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        Document doc = this.findWebEngine().getDocument();
        if (doc == null) {
            return null;
        }

        return doc.getElementById(id);
    }

    /**
     * 根据节点标签获取所有节点，可能返回空列表对象！
     */
    public final List<Element> findElementsByTag(String tag) {
        List<Element> eles = new ArrayList<Element>();

        if (StringUtils.isBlank(tag)) {
            return eles;
        }

        Document doc = this.findWebEngine().getDocument();
        if (doc == null) {
            return eles;
        }

        NodeList nodes = doc.getElementsByTagName(tag);
        if (nodes == null) {
            return eles;
        }

        int count = nodes.getLength();
        for (int i = 0; i < count; i++) {
            eles.add((Element) nodes.item(i));
        }

        return eles;
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
