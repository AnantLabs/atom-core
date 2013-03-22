/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Key/Value工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: MapUtils.java, V1.0.1 2013-3-22 下午9:26:57 $
 */
public class MapUtils {

    /**
     * 从XML文件中解析Key/Value内容
     */
    public static final Map<String, String> fromXML(String path) {
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            Properties props = new Properties();
            props.loadFromXML(input);

            Map<String, String> map = new ConcurrentHashMap<String, String>();
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                String key = StringUtils.trimToEmpty(ObjectUtils.toString(entry.getKey()));
                String value = StringUtils.trimToEmpty(ObjectUtils.toString(entry.getValue()));

                map.put(key, value);
            }

            return map;
        } catch (Exception e) {
            throw new RuntimeException("从XML文件[" + path + "]中解析Map异常!");
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

}
