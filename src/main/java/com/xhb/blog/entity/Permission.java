package com.xhb.blog.entity;

import com.xhb.blog.mapper.PermissionMapper;

public class Permission {
    private Integer id;
    private Integer u_id;
    private String permission;

    public Permission() {
    }

    public Permission(Integer id, Integer u_id, String permission) {
        this.id = id;
        this.u_id = u_id;
        this.permission = permission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
