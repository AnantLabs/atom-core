/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.atom.core.lang.utils.LogUtils;

/**
 * WebView基础视图，扩展并增加了通用方法。
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseWebView.java, V1.0.1 2013-2-6 下午12:57:15 $
 */
public abstract class BaseWebView {
    /** Web页面是否初始化成功标识 */
    private final AtomicBoolean _init = new AtomicBoolean(false);

    /** Web组件 */
    private final WebView       webView;
    private final WebEngine     webEngine;

    /**
     * CTOR
     */
    public BaseWebView(String url) {
        this.webView = new WebView();
        this.webEngine = this.webView.getEngine();
        this.webEngine.load(url);
        
        this.webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.FAILED) {
                    _init.set(false);
                    onWebLoadFailed();
                } else if (newState == State.SUCCEEDED) {
                    _init.set(true);
                    onWebLoadSucceeded();
                }
            }
        });
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
        if (!this._init.get()) {
            return null;
        }

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

        if (!this._init.get()) {
            return eles;
        }

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
     * 在Web页面上执行脚本
     */
    public final void executeScript(String script) {
        if (!this._init.get()) {
            LogUtils.warn("Web页面未成功加载，无法执行脚本[" + script + "].");
            return;
        }

        try {
            this.webEngine.executeScript(script);
        } catch (Exception e) {
            String msg = "执行脚本[" + script + "]发生异常！";
            LogUtils.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    /**
     * 根据ID停止Video视频
     */
    public final void stopVideo(String id) {
        StringBuilder txt = new StringBuilder(128);
        txt.append("document.getElementById('").append(id).append("').currentTime = 0;");
        txt.append("document.getElementById('").append(id).append("').pause();");

        this.executeScript(txt.toString());
    }

    /**
     * 根据标签停止所有Video视频
     */
    public final void stopAllVideos() {
        StringBuilder txt = new StringBuilder(256);
        txt.append("var videos = document.getElementsByTagName('video');");
        txt.append("for(var i = 0; i < videos.length; i++) {");
        txt.append("    videos[i].currentTime = 0;");
        txt.append("    videos[i].pause();");
        txt.append("}");

        this.executeScript(txt.toString());
    }

    /**
     * 停止Audio音频
     */
    public final void stopAudio(String id) {
        this.stopVideo(id);
    }
    
    /**
     * 根据标签停止所有Audio音频
     */
    public final void stopAllAudios() {
        StringBuilder txt = new StringBuilder(256);
        txt.append("var audios = document.getElementsByTagName('audio');");
        txt.append("for(var i = 0; i < audios.length; i++) {");
        txt.append("    audios[i].currentTime = 0;");
        txt.append("    audios[i].pause();");
        txt.append("}");

        this.executeScript(txt.toString());
    }

}
