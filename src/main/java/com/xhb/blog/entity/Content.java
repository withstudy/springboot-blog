package com.xhb.blog.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Content {

    private Integer id;
    private Integer c_id;
    private Integer u_id;
    private String u_name;
    private String title;
    private String description;
    private String content;
    private Integer views;
    private Integer comments;

    public Content(Integer c_id, Integer u_id,
                   String u_name, String title, String description, String content,
                   Integer views, Integer comments, String addTime) {
        this.c_id = c_id;
        this.u_id = u_id;
        this.u_name = u_name;
        this.title = title;
        this.description = description;
        this.content = content;
        this.views = views;
        this.comments = comments;
        this.addTime = addTime;
    }

    private String addTime;


    public Content() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}
