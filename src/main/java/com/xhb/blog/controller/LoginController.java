package com.xhb.blog.controller;

import com.xhb.blog.entity.ActiveUser;
import com.xhb.blog.entity.User;
import com.xhb.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Api(value = "LoginController", description = "登录管理")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ApiIgnore()
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    @ApiOperation(value="login",notes = "跳转到登录页")
    public ModelAndView login() {
        logger.info("login");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @PostMapping("/login")
    @ApiOperation(value="login",notes = "登录验证")
    public ModelAndView login(String username, String password, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        //用户名不能为空
        if (username == null || "".equals(username)) {
            mav.addObject("usernameErrMsg", "请输入用户名");
            mav.addObject("username", username);
            mav.addObject("password", password);
            mav.setViewName("login");
            return mav;
        }
        //密码不能为空
        if (password == null || "".equals(password)) {
            mav.addObject("passwordErrMsg", "请输入密码");
            mav.addObject("password", password);
            mav.addObject("username", username);
            mav.setViewName("login");
            return mav;
        }
        //查询条件封装
        Subject subject= SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            mav.addObject("passwordErrMsg", "用户名不存在");
            mav.addObject("password", password);
            mav.addObject("username", username);
            mav.setViewName("login");
            return mav;
        }catch (IncorrectCredentialsException e){
            mav.addObject("passwordErrMsg", "用户名或密码不正确");
            mav.addObject("password", password);
            mav.addObject("username", username);
            mav.setViewName("login");
            return mav;
        }catch (AccountException e){
            mav.addObject("passwordErrMsg", "用户名或密码不正确");
            mav.addObject("password", password);
            mav.addObject("username", username);
            mav.setViewName("login");
            return mav;
        }
//        User user = userService.userLogin(username, password);
//        //没有该用户
//        if (user == null) {
//            mav.addObject("passwordErrMsg", "用户名或密码不正确");
//            mav.addObject("password", password);
//            mav.addObject("username", username);
//            mav.setViewName("login");
//            return mav;
//        }
//        //不是管理员
//        if (user.getIsAdmin() == 0) {
//            mav.addObject("passwordErrMsg", "您不是管理员");
//            mav.addObject("password", password);
//            mav.addObject("username", username);
//            mav.setViewName("login");
//            return mav;
//        }
        //有该用户且时管理员
        //System.out.println(username);
        if (subject.isAuthenticated()) {
            ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
            session.setAttribute("user", activeUser.getUser());
            mav.setViewName("redirect:/blog/index");
            return mav;
        } else {
            token.clear();
            mav.addObject("passwordErrMsg", "登录失败");
            mav.addObject("password", password);
            mav.addObject("username", username);
            mav.setViewName("login");
            return mav;
        }

    }


}
