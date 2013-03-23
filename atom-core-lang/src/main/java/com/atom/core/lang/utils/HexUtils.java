/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 16进制工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: HexUtils.java, V1.0.1 2013-3-23 下午12:55:36 $
 */
public class HexUtils {

    /**
     * 整形转换为Hex字符
     */
    public static final String toHex(int data) {
        return StringUtils.upperCase(StringUtils.leftPad(Integer.toHexString(data), 2, "0"));
    }

    /**
     * 整形数组转换为Hex字符数组
     */
    public static final String[] toHexs(int[] data) {
        String[] hexs = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            hexs[i] = toHex(data[i]);
        }

        return hexs;
    }

    /**
     * 整形数组转换为Hex字符数串
     */
    public static final String toHex(int[] data) {
        return toHex(data, data.length);
    }

    /**
     * 整形数组转换为Hex字符数串
     */
    public static final String toHex(int[] data, int size) {
        if (data.length < size) {
            size = data.length;
        }

        StringBuilder txt = new StringBuilder();

        for (int i = 0; i < size; i++) {
            txt.append(toHex(data[i])).append(" ");
        }

        return StringUtils.trim(txt.toString());
    }

}
