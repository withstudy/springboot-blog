package com.xhb.blog.controller;

import com.xhb.blog.entity.Category;
import com.xhb.blog.entity.Page;
import com.xhb.blog.entity.User;
import com.xhb.blog.service.CategoryService;
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
import java.util.List;

@Controller
@RequestMapping("/blog")
@Api(value = "CategoryController", description = "分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public Integer findCategoryAll() {
        return categoryService.findCategoryAll().size();
    }

    @GetMapping("/category/index")
    @ApiOperation(value="index",notes = "首页")
    public String index() {
        return "redirect:/blog/category/categorys";
    }

    @GetMapping("/category/categorys")
    @ApiOperation(value="categorys",notes = "评论列表及筛选")
    public ModelAndView categorys(Integer currPage, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        Page<Category> page = new Page<Category>();
        if (currPage == null) {
            page.setCurrPage(1);
        } else {
            page.setCurrPage(currPage);
        }
        page.setTotalCount(findCategoryAll());
        page.setTotalPage();
        page.setStart();
        page.setLists(categoryService.findCategoryPage(page));//**

        mav.addObject("user", ouser);
        mav.addObject("page", page);
        mav.addObject("controller", "category/categorys");
        mav.setViewName("category/index");
        return mav;
    }

    @GetMapping("/category/delete/{id}")
    @ApiOperation(value="delete",notes = "分类删除")
    public String delete(@PathVariable("id") Integer id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/blog/category/categorys";
    }

    @GetMapping("/category/update/{id}")
    @ApiOperation(value="update",notes = "查找修改分类")
    public ModelAndView update(@PathVariable("id") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        Category category = null;
        if (id == -1) {
            category = new Category();
            mav.setViewName("category/add");
        } else {
            category = categoryService.findCategoryById(id);
            mav.setViewName("category/edit");
        }
        mav.addObject("user", ouser);
        mav.addObject("category", category);
        return mav;
    }

    @PostMapping("/category/update")
    @ApiOperation(value="update",notes = "修改分类")
    public ModelAndView update(Category category, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        //名称不能为空
        if (category.getName() == null || "".equals(category.getName())) {
            if (category.getId() != null) {
                mav.setViewName("category/edit");
            } else {
                mav.setViewName("category/add");
            }
            mav.addObject("nameErrMsg", "名称不能为空");
            mav.addObject("category", category);
            mav.addObject("user", ouser);
            return mav;
        }
        //已存在
        List<Category> cs = categoryService.findCategoryByName(category.getName());
        if (category.getId() == null && cs.size() != 0) {
            mav.setViewName("category/add");
            mav.addObject("nameErrMsg", "已存在");
            mav.addObject("category", category);
            mav.addObject("user", ouser);
            return mav;
        }
        //简介不能少于10个字符
        if (category.getContent() == null || category.getContent().length() < 10) {
            if (category.getId() != null) {
                mav.setViewName("category/edit");
            } else {
                mav.setViewName("category/add");
            }
            mav.addObject("contentErrMsg", "简介不能少于10个字符");
            mav.addObject("category", category);
            mav.addObject("user", ouser);
            return mav;
        }
        if (category.getId() != null) {
            categoryService.updateCategory(category);
        } else {
            categoryService.addCategory(category);
        }
        mav.setViewName("redirect:/blog/category/categorys");
        return mav;
    }
}
