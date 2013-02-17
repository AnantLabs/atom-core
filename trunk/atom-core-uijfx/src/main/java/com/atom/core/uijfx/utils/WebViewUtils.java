/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * WebView工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebViewUtils.java, V1.0.1 2013-2-8 下午9:26:53 $
 */
public final class WebViewUtils {
    
    /**
     * 加载或初始化WebView视图对象
     */
    public static WebView initWebView(String url) {
        WebView webView = new WebView();
        webView.getEngine().load(url);

        return webView;
    }

    /**
     * 注册Web页面加载‘失败’事件
     */
    public static void registWebStateFailedEvent(WebView webView, final WebViewStateEvent event) {
        if (webView == null) {
            throw new RuntimeException("注册WebView加载成功事件失败，WebView对象为NULL.");
        }

        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.FAILED) {
                    event.onWebViewStateChanged(oldState, newState);
                }
            }
        });
    }

    /**
     * 注册Web页面加载‘成功’事件
     */
    public static void registWebStateSucceedEvent(WebView webView, final WebViewStateEvent event) {
        if (webView == null) {
            throw new RuntimeException("注册WebView加载成功事件失败，WebView对象为NULL.");
        }

        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @SuppressWarnings("rawtypes")
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    event.onWebViewStateChanged(oldState, newState);
                }
            }
        });
    }

    /**
     * 存放Java变量到HTML的JavaScript的Window对象中
     */
    public static void putHtmlWindowObject(WebView webView, String varName, Object object) {
        if (webView == null) {
            throw new RuntimeException("存放变更到HTML中失败，WebView对象为NULL.");
        }

        if (StringUtils.isBlank(varName)) {
            throw new RuntimeException("存放变更到HTML中失败，HTML变量名为空.");
        }

        if (object == null) {
            throw new RuntimeException("存放变更到HTML中失败，HTML变量值为NULL.");
        }

        JSObject window = (JSObject) executeScript(webView, "window");
        window.setMember(varName, object);
    }

    /**
     * 根据ID获取节点信息，可能返回NULL对象！
     */
    public static Element findElementByID(WebView webView, String id) {
        if (webView == null) {
            throw new RuntimeException("根据ID获取节点信息失败，WebView对象为NULL.");
        }

        if (StringUtils.isBlank(id)) {
            return null;
        }

        Document doc = webView.getEngine().getDocument();
        if (doc == null) {
            return null;
        }

        return doc.getElementById(id);
    }

    /**
     * 根据节点标签获取所有节点，可能返回空列表对象！
     */
    public static List<Element> findElementsByTag(WebView webView, String tag) {
        if (webView == null) {
            throw new RuntimeException("根据节点标签获取所有节点失败，WebView对象为NULL.");
        }

        List<Element> eles = new ArrayList<Element>();

        if (StringUtils.isBlank(tag)) {
            return eles;
        }

        Document doc = webView.getEngine().getDocument();
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
    public static Object executeScript(WebView webView, String script) {
        if (webView == null) {
            throw new RuntimeException("执行脚本[" + script + "]失败，WebView对象为NULL.");
        }

        try {
            return webView.getEngine().executeScript(script);
        } catch (Exception e) {
            throw new RuntimeException("执行脚本[" + script + "]发生异常！", e);
        }
    }

    /**
     * 根据ID停止Video视频
     */
    public static void stopVideo(WebView webView, String id) {
        StringBuilder txt = new StringBuilder(128);
        txt.append("document.getElementById('").append(id).append("').currentTime = 0;");
        txt.append("document.getElementById('").append(id).append("').pause();");

        executeScript(webView, txt.toString());
    }

    /**
     * 根据标签停止所有Video视频
     */
    public static void stopAllVideos(WebView webView) {
        StringBuilder txt = new StringBuilder(256);
        txt.append("var videos = document.getElementsByTagName('video');");
        txt.append("for(var i = 0; i < videos.length; i++) {");
        txt.append("    videos[i].currentTime = 0;");
        txt.append("    videos[i].pause();");
        txt.append("}");

        executeScript(webView, txt.toString());
    }

    /**
     * 停止Audio音频
     */
    public static void stopAudio(WebView webView, String id) {
        stopVideo(webView, id);
    }

    /**
     * 根据标签停止所有Audio音频
     */
    public static void stopAllAudios(WebView webView) {
        StringBuilder txt = new StringBuilder(256);
        txt.append("var audios = document.getElementsByTagName('audio');");
        txt.append("for(var i = 0; i < audios.length; i++) {");
        txt.append("    audios[i].currentTime = 0;");
        txt.append("    audios[i].pause();");
        txt.append("}");

        executeScript(webView, txt.toString());
    }

    /**
     * WebView状态改变事件
     */
    public static interface WebViewStateEvent {
        /** 状态改变 */
        public void onWebViewStateChanged(State oldState, State newState);
    }

}
