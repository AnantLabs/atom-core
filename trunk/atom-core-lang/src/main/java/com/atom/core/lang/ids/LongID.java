/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.ids;

import com.atom.core.lang.ToString;

/**
 * ID接口Long实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: LongID.java, 2012-8-18 下午8:25:49 Exp $
 */
public class LongID extends ToString implements ID<Long>, Comparable<LongID> {
    private static final long serialVersionUID = 6563737303459840471L;

    /** ID */
    private long              id;

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
    public final Long getId() {
        return this.id;
    }

    /** 
     * @see com.atom.core.lang.ids.ID#setId(java.lang.Object)
     */
    public final void setId(Long id) {
        this.id = id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    /** 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.getId().hashCode();
    }

    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!LongID.class.isAssignableFrom(other.getClass())) {
            return false;
        }

        return this.getId() == ((LongID) other).getId();
    }

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public final int compareTo(LongID other) {
        if (other == null) {
            return 1;
        }

        return (int) (this.getId() - other.getId());
    }

}
