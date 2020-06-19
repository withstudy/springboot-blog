package com.xhb.blog.controller;

import com.xhb.blog.entity.Page;
import com.xhb.blog.entity.User;
import com.xhb.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/blog")
@Api(value = "UserController", description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户管理首页")
    @GetMapping("/user/index")
    public String index() {
        return "redirect:/blog/user/users";
    }

    public Integer findUserAll() {
        List<User> lUser = new ArrayList<User>();
        lUser = userService.findUserAll();
        return lUser.size();
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/user/get/{id}")
    public ModelAndView findUserById(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        User fUser = new User();

        return mav;
    }

    /**
     * 分页列表
     *
     * @param currPage
     * @param request
     * @return
     */
    @ApiOperation(value = "获取用户列表")
    @RequestMapping("/user/users")
    public ModelAndView findUserByPage(Integer currPage, HttpServletRequest request) {
        //System.out.println(currPage);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        Page<User> page = new Page<User>();
        if (currPage == null) {
            page.setCurrPage(1);
        } else {
            page.setCurrPage(currPage);
        }
        page.setTotalCount(findUserAll());
        page.setTotalPage();
        page.setStart();
        page.setLists(userService.findUserByPage(page));//**

        mav.addObject("user", user);
        mav.addObject("page", page);
        mav.addObject("controller", "user/users");
        mav.setViewName("user/index");
        return mav;
    }

    @ApiOperation(value = "删除用户")
    @GetMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/blog/user/users";
    }

    @ApiOperation(value = "修改用户")
    @GetMapping("/user/update/{id}")
    public ModelAndView updateUser(@PathVariable("id") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        User user = null;
        if (id == -1) {
            user = new User();
            mav.setViewName("user/add");
        } else {
            user = userService.findUserById(id);
            mav.setViewName("user/edit");
        }
        mav.addObject("user", ouser);
        mav.addObject("nuser", user);
        return mav;
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/user/update")
    public ModelAndView updateUserById(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            if (user.getId() != null) {
                mav.setViewName("user/edit");
            } else {
                mav.setViewName("user/add");
            }
            mav.addObject("nuser", user);
            mav.addObject("user", ouser);
            mav.addObject("usernameErrMsg", "用户名不能为空");
            return mav;
        }
        User nUser = userService.findUserByName(user.getUsername());
        if(null != nUser){
            if (user.getId() != null) {
                mav.setViewName("user/edit");
            } else {
                mav.setViewName("user/add");
            }
            mav.addObject("nuser", user);
            mav.addObject("user", ouser);
            mav.addObject("usernameErrMsg", "用户名已存在");
            return mav;
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            if (user.getId() != null) {
                mav.setViewName("user/edit");
            } else {
                mav.setViewName("user/add");
            }
            mav.addObject("nuser", user);
            mav.addObject("user", ouser);
            mav.addObject("passwordErrMsg", "密码不能为空");
            return mav;
        }
        if (user.getIsAdmin() == null || "".equals(user.getIsAdmin())) {
            user.setIsAdmin(0);
        }
        if (user.getId() != null) {
            userService.updateUser(user);
        } else {
            user.setPassword( new Md5Hash(user.getPassword(),user.getUsername(),2).toString() );
            userService.addUser(user);

        }
        mav.setViewName("redirect:/blog/user/users");
        return mav;
    }

//    @GetMapping("/user/add")
//    public ModelAndView addUser(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        String username= (String) session.getAttribute("username");
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("username",username);
//        mav.setViewName("user/add");
//        return mav;
//    }

//    @PostMapping("/user/add")
//    public ModelAndView addUser(User user,HttpServletRequest request){
//        HttpSession session = request.getSession();
//        String username= (String) session.getAttribute("username");
//        ModelAndView mav = new ModelAndView();
//        if(user.getUsername()== null || "".equals(user.getUsername())){
//            mav.setViewName("user/edit");
//            mav.addObject("user",user);
//            mav.addObject("username",username);
//            mav.addObject("usernameErrMsg","用户名不能为空");
//            return mav;
//        }
//        if(user.getPassword()== null || "".equals(user.getPassword())){
//            mav.setViewName("user/edit");
//            mav.addObject("user",user);
//            mav.addObject("username",username);
//            mav.addObject("passwordErrMsg","密码不能为空");
//            return mav;
//        }
//        if(user.getIsAdmin()==null || "".equals(user.getIsAdmin())){
//            user.setIsAdmin(0);
//        }
//        userService.updateUser(user);
//        mav.setViewName("redirect:/blog/user/users");
//        return mav;
//    }

    @GetMapping("/exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        return "redirect:/login";
    }
}
