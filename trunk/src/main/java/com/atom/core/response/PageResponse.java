/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回结果
 * 
 * @author obullxl@gmail.com
 * @version $Id: PageResponse.java, 2012-8-12 下午9:41:21 Exp $
 */
public class PageResponse extends Response {
    private static final long serialVersionUID = -5790884726739268661L;
    
    /** 页号 */
    private int pageNo;
    
    /** 页大小 */
    private int pageSize;
    
    /** 总条数 */
    private int totalSize;
    
    /** 总页数 */
    private int totalPage;
    
    /** 数据明细 */
    private List<Object> items;

    // ~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~ //
    
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Object> getItems() {
        if(this.items == null) {
            this.items = new ArrayList<Object>();
        }
        
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }
    
    
}
