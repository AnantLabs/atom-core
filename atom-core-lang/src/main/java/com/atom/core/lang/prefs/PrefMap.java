/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.prefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.SystemUtils;

/**
 * 
 * @author shizihu
 * @version $Id: PrefMap.java, v 0.1 2013-1-28 上午10:19:22 shizihu Exp $
 */
public class PrefMap {
    private final Map<String, String> _map = new ConcurrentHashMap<String, String>();
    private final String              name;

    public PrefMap(String name) {
        this.name = name;
        this.init();
    }

    private void init() {
        String path = this.getFilePath();
        File file = new File(path + "/" + this.name);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            Properties props = new Properties();
            props.loadFromXML(input);
            this.toMap(props);
        } catch (Exception e) {
            throw new RuntimeException("初始化PrefMap[" + this.name + "]异常.", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    public void store() {
        String path = this.getFilePath();
        File file = new File(path + "/" + this.name);
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            Properties props = new Properties();
            this.toProperties(props);
            props.storeToXML(output, "", "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("存储PrefMap[" + this.name + "]异常.", e);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

    public String get(String key) {
        return this._map.get(key);
    }

    public void put(String key, String value) {
        this._map.put(key, value);
        this.store();
    }

    public String remove(String key) {
        String value = this._map.remove(key);
        this.store();
        
        return value;
    }

    public boolean getBoolean(String key) {
        return BooleanUtils.toBoolean(this.get(key));
    }

    private String getFilePath() {
        return FilenameUtils.normalizeNoEndSeparator(SystemUtils.getUserHome().getAbsolutePath());
    }
    
    private void toMap(Properties props) {
        for(Map.Entry<Object, Object> entry : props.entrySet()) {
            this._map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
    }
    
    private void toProperties(Properties props) {
        for(Map.Entry<String, String> entry : this._map.entrySet()) {
            props.put(entry.getKey(), entry.getValue());
        }
    }

}
