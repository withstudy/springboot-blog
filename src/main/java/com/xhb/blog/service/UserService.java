package com.xhb.blog.service;

import com.xhb.blog.entity.Page;
import com.xhb.blog.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


public interface UserService {
    public User userLogin(String username, String password);

    public List<User> findUserAll();

    public List<User> findUserByPage(Page page);

    public void deleteUserById(Integer id);

    public User findUserById(Integer id);

    public void updateUser(User user);

    public void addUser(User user);


    public User findUserByName(String name);

}
