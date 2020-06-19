package com.xhb.blog.service;

import com.xhb.blog.entity.Permission;

import java.util.List;

public interface PermissionService {
    public List<String> findPermissionByUid(Integer uid);
}
