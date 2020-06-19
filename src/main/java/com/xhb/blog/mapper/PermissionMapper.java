package com.xhb.blog.mapper;

import com.xhb.blog.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value = "permissionMapper")
public interface PermissionMapper {
    public List<String> findPermissionByUid(Integer uid);
}
