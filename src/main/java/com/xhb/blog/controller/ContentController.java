package com.xhb.blog.controller;

import com.xhb.blog.entity.Category;
import com.xhb.blog.entity.Content;
import com.xhb.blog.entity.Page;
import com.xhb.blog.entity.User;
import com.xhb.blog.service.CategoryService;
import com.xhb.blog.service.CommentsService;
import com.xhb.blog.service.ContentService;
import com.xhb.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
@Api(value = "ContentController", description = "内容管理")
public class ContentController {
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentsService commentsService;

    public Integer findContentAll(String where) {
        return contentService.findContentAll(where).size();
    }

    @GetMapping("/content/index")
    @ApiOperation(value="index",notes = "首页")
    public String index() {
        return "redirect:/blog/content/contents";
    }

    @GetMapping("/content/contents")
    @ApiOperation(value="contents",notes = "内容列表及筛选")
    public ModelAndView contents(Integer currPage,String where, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        Page<Content> page = new Page<Content>();
        if (currPage == null) {
            page.setCurrPage(1);
        } else {
            page.setCurrPage(currPage);
        }
        if(null != where){
            page.setTotalCount(findContentAll(where));
            page.setWhere2(where);
        }else{
            page.setTotalCount(findContentAll(null));
            page.setWhere2(null);
        }
        page.setTotalPage();
        page.setStart();
        if(page.getTotalCount()>0){
            page.setLists(contentService.findContentByPage(page));//**
        }else{
            page.setLists(new ArrayList<>());
        }

        List<User> users = userService.findUserAll();
        List<Category> categorys = categoryService.findCategoryAll();
        mav.addObject("users", users);
        mav.addObject("categorys", categorys);
        mav.addObject("user", ouser);
        mav.addObject("page", page);
        mav.addObject("controller", "content/contents");
        mav.setViewName("content/index");
        return mav;
    }

    @GetMapping("/content/delete/{id}")
    @ApiOperation(value="delete",notes = "内容删除")
    public String delete(@PathVariable("id") Integer id) {
        commentsService.deleteCommentsByCid(id);
        contentService.deleteContentById(id);
        return "redirect:/blog/content/contents";
    }

    @GetMapping("/content/update/{id}")
    @ApiOperation(value="update",notes = "查找修改内容")
    public ModelAndView update(@PathVariable("id") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        Content content = null;
        List<User> users = new ArrayList<User>();
        if (id == -1) {
            content = new Content();
            users.add(ouser);
            mav.setViewName("content/edit");
        } else {
            content = contentService.findContentById(id);
            users = userService.findUserAll();
            mav.setViewName("content/edit");
        }

        List<Category> categorys = categoryService.findCategoryAll();
        mav.addObject("users", users);
        mav.addObject("categorys", categorys);
        mav.addObject("user", ouser);
        mav.addObject("content", content);
        return mav;
    }

    @PostMapping("/content/update")
    @ApiOperation(value="update",notes = "内容修改")
    public ModelAndView update(Content content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", ouser);
        List<User> users = userService.findUserAll();
        List<Category> categorys = categoryService.findCategoryAll();
        mav.addObject("users", users);
        mav.addObject("categorys", categorys);
        //标题为空
        if (content.getTitle() == null || "".equals(content.getTitle())) {
            mav.addObject("titleErrMsg", "标题不能为空");
            mav.addObject("content", content);
            mav.setViewName("content/edit");
            return mav;
        }
        //简介不能少于10个字符
        if (content.getDescription() == null || content.getDescription().length() < 10) {
            mav.addObject("descriptionErrMsg", "简介不能少于10个字符");
            mav.addObject("content", content);
            mav.setViewName("content/edit");
            return mav;
        }
        //内容不能少于10个字符
        if (content.getContent() == null || content.getContent().length() < 10) {
            mav.addObject("contentErrMsg", "内容不能少于10个字符");
            mav.addObject("content", content);
            mav.setViewName("content/edit");
            return mav;
        }
        if (content.getId() == null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            content.setAddTime(sdf.format(date));
            contentService.addContent(content);
        } else {
            contentService.updateContent(content);
        }
        mav.setViewName("redirect:/blog/content/contents");
        return mav;
    }
}
