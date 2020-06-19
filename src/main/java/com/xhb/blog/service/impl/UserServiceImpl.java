package com.xhb.blog.service.impl;

import com.xhb.blog.entity.Page;
import com.xhb.blog.entity.User;
import com.xhb.blog.mapper.UserMapper;
import com.xhb.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(String username, String password) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("password", password);
        User user = userMapper.findUser(params);
        return user;
    }

    public List<User> findUserAll() {
        return userMapper.findUserAll();
    }

    @Override
    public List<User> findUserByPage(Page page) {
        return userMapper.findUserByPage(page);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }


}
