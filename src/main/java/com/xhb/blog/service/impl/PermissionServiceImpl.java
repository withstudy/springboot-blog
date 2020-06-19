package com.xhb.blog.service.impl;

import com.xhb.blog.entity.Permission;
import com.xhb.blog.mapper.PermissionMapper;
import com.xhb.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xhb.blog.service.PermissionService;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> findPermissionByUid(Integer uid) {
        return permissionMapper.findPermissionByUid(uid);
    }
}
