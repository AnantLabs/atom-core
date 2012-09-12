/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.response;

import com.atom.core.ToString;

/**
 * 返回结果
 * 
 * @author obullxl@gmail.com
 * @version $Id: Response.java, 2012-8-12 上午10:28:33 Exp $
 */
public class Response extends ToString {
    private static final long serialVersionUID = -2418639446247995926L;

    /** 成功标志 */
    private boolean           success          = true;

    /** 错误码 */
    private String            errCode;

    /** 错误描述 */
    private String            errDesp;

    // ~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrDesp() {
        return errDesp;
    }

    public void setErrDesp(String errDesp) {
        this.errDesp = errDesp;
    }

}
