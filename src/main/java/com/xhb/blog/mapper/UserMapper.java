package com.xhb.blog.mapper;

import com.xhb.blog.entity.Page;
import com.xhb.blog.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "userMapper")
public interface UserMapper {
    public User findUser(Map params);

    public List<User> findUserAll();

    public List<User> findUserByPage(Page page);

    public void deleteUserById(Integer id);

    public User findUserById(Integer id);

    public void updateUser(User user);

    public void addUser(User user);

    public User findUserByName(String name);
}
