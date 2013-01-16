/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.ids;

/**
 * ID接口Long实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: LongID.java, 2012-8-18 下午8:25:49 Exp $
 */
public class LongID implements ID<Long> {

    /** ID */
    private long id;

    /**
     * CTOR
     */
    public LongID() {
        this(0L);
    }

    public LongID(long id) {
        this.id = id;
    }

    /** 
     * @see com.atom.core.lang.ids.ID#getId()
     */
    public Long getId() {
        return this.id;
    }

    /** 
     * @see com.atom.core.lang.ids.ID#setId(java.lang.Object)
     */
    public void setId(Long id) {
        this.id = id;
    }

}
