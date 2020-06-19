package com.xhb.blog.entity;

import java.util.List;

public class Page<T> {
    private int currPage;//当前页数
    private int pageSize = 5;//每页显示的记录数
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private List<T> lists;//每页的显示的数据
    private int start;
    private Object where;
    private Object where2;

    public int getStart() {
        return start;
    }

    public void setStart() {

        this.start = (this.currPage - 1) * this.pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        if (this.totalCount % this.pageSize == 0) {
            this.totalPage = this.totalCount / this.pageSize;
        } else {
            this.totalPage = this.totalCount / this.pageSize + 1;
        }

    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public Object getWhere() {
        return where;
    }

    public void setWhere(Object where) {
        this.where = where;
    }

    public Object getWhere2() {
        return where2;
    }

    public void setWhere2(Object where2) {
        this.where2 = where2;
    }
}
