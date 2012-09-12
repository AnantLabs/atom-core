/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.converter;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

/**
 * JSON数据转换
 * 
 * @author obullxl@gmail.com
 * @version $Id: HttpMessageConverterJSON.java, 2012-8-13 下午10:24:58 Exp $
 */
public final class HttpMessageConverterJSON extends MappingJacksonHttpMessageConverter implements InitializingBean {

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        this.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

}
