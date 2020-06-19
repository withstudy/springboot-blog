package com.xhb.blog.entity;

import java.util.Date;

public class Comments {
    private Integer id;
    private Integer con_id;
    private String comments;
    private String addTime;
    private String username;

    public Comments(Integer con_id, String comments, String addTime, String username) {
        this.con_id = con_id;
        this.comments = comments;
        this.addTime = addTime;
        this.username = username;
    }

    public Comments() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCon_id() {
        return con_id;
    }

    public void setCon_id(Integer con_id) {
        this.con_id = con_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
