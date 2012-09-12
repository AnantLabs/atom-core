/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.exception;

import com.atom.core.KeyValue;

/**
 * 业务异常
 * 
 * @author obullxl@gmail.com
 * @version $Id: BizException.java, 2012-8-18 下午3:33:29 Exp $
 */
public final class BizException extends Exception {
    private static final long serialVersionUID = -8332455700538716278L;

    /** 错误码 */
    private final KeyValue    error;

    public BizException(KeyValue error) {
        this.error = error;
    }

    public KeyValue getError() {
        return error;
    }

}
