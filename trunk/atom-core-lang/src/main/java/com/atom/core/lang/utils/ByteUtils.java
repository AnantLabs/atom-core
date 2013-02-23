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

    /**
     * The high digits lookup table.
     */
    private static final byte[] highDigits;

    /**
     * The low digits lookup table.
     */
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

}
