/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 字节工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: ByteUtils.java, V1.0.1 2013-2-23 下午11:08:03 $
 */
public final class ByteUtils {
    /** The high digits lookup table. */
    private static final byte[] highDigits;

    /** The low digits lookup table. */
    private static final byte[] lowDigits;

    /**
     * Initialize lookup tables.
     */
    static {
        final byte[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        int i;
        byte[] high = new byte[256];
        byte[] low = new byte[256];

        for (i = 0; i < 256; i++) {
            high[i] = digits[i >>> 4];
            low[i] = digits[i & 0x0F];
        }

        highDigits = high;
        lowDigits = low;
    }

    /**
     * Dumps an byte array to a hex formatted string.
     */
    public static String toHex(byte[] data) {
        if (data == null) {
            return "null";
        }

        if (data.length == 0) {
            return "empty";
        }

        StringBuilder out = new StringBuilder();

        for (byte value : data) {
            int byteValue = value & 0xFF;
            out.append((char) highDigits[byteValue]);
            out.append((char) lowDigits[byteValue]);
            out.append(' ');
        }

        return StringUtils.trim(out.toString());
    }
    
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
