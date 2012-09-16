/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.response;

import java.util.ArrayList;
import java.util.List;

import com.atom.core.Paginator;
import com.atom.core.ToString;

/**
 * 分页列表
 * 
 * @author obullxl@gmail.com
 * @version $Id: PageList.java, 2012-9-16 下午5:43:10 Exp $
 */
public class PageList<T> extends ToString {
    private static final long serialVersionUID = 962447196061108847L;

    /** 分页器 */
    private Paginator         paginator;

    /** 对象列表 */
    private List<T>           items;

    // ~~~~~~~~~~ CTOR ~~~~~~~~~~ //

    public PageList() {
        this.paginator = new Paginator();
    }

    public PageList(Paginator paginator) {
        this.paginator = paginator;
    }

    public PageList(Paginator paginator, List<T> items) {
        this.paginator = paginator;
        this.items = items;
    }

    // ~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public List<T> getItems() {
        if (this.items == null) {
            this.items = new ArrayList<T>();
        }

        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
