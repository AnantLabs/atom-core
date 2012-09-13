/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.exception;

/**
 * 业务异常
 * 
 * @author obullxl@gmail.com
 * @version $Id: BizException.java, 2012-8-18 下午3:33:29 Exp $
 */
public final class BizException extends Exception {
    private static final long serialVersionUID = -8332455700538716278L;

    /** 错误码 */
    private final String      errCode;

    /** 错误描述 */
    private final String      errDesp;

    public BizException(String errCode) {
        this(errCode, errCode);
    }

    public BizException(String errCode, String errDesp) {
        super(errCode);
        
        this.errCode = errCode;
        this.errDesp = errDesp;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesp() {
        return errDesp;
    }

}
