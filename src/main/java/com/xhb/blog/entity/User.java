package com.xhb.blog.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer isAdmin;
    private String img;

    public User(String username, String password, Integer isAdmin, String img) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.img = img;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
