package com.xhb.blog.controller;

import com.xhb.blog.entity.*;
import com.xhb.blog.service.CommentsService;
import com.xhb.blog.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/blog")
@Api(value = "CommentsController", description = "评论管理")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ContentService contentService;

    public Integer findCommentsAll(Integer con_id,String where){
        return commentsService.findCommentsAll(con_id,where).size();
    }

    @GetMapping("/comment/index")
    @ApiOperation(value="index",notes = "首页")
    public String index(){
        return "redirect:/blog/comment/comments";
    }

    @RequestMapping("/comment/comments")
    @ApiOperation(value="comments",notes = "评论列表及筛选")
    public ModelAndView comments(Integer currPage, Integer cid,String where,HttpServletRequest request){
        HttpSession session = request.getSession();
        //获取已登录的用户
        User ouser = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        Page<Comments> page = new Page<Comments>();
        //如果currPage 为null 第一页
        if (currPage == null) {
            page.setCurrPage(1);
        } else {
            page.setCurrPage(currPage);
        }

        page.setStart();
        List<Content> contents=null;
        //如果 cid 不为空 查询所有
        //如果 cid 为空 添加条件查询
        if(cid!=null && null != where){
            //有cid 和 where
            page.setTotalCount(findCommentsAll(cid,where));
            page.setWhere2(where);
            page.setWhere(cid);

            contents=new ArrayList<Content>();
            contents.add(contentService.findContentById(cid));
        }else if(cid ==null && null != where){
            //只有where
            page.setTotalCount(findCommentsAll(null,where));
            page.setWhere2(where);

            contents=contentService.findContentAll(null);

        }else if(cid!=null && null == where){
            //cid
            page.setTotalCount(findCommentsAll(cid,null));
            page.setWhere(cid);

            contents=new ArrayList<Content>();
            contents.add(contentService.findContentById(cid));
        }else{
            //都没有
            page.setTotalCount(findCommentsAll(null,null));
            contents=contentService.findContentAll(null);
        }


        if(commentsService.findCommentsPage(page).size() > 0){
            page.setLists(commentsService.findCommentsPage(page));
        }else{
            page=new Page<Comments>();
            page.setTotalCount(0);
            page.setCurrPage(0);
            page.setLists(new ArrayList<Comments>());
        }


        page.setTotalPage();
        mav.addObject("contents", contents);
        mav.addObject("user", ouser);
        mav.addObject("page", page);
        mav.addObject("controller", "comment/comments");
        mav.setViewName("comment/index");
        return mav;
    }



    @GetMapping("/comment/delete/{id}")
    @ApiOperation(value="delete",notes = "评论删除")
    public String delete(@PathVariable("id") Integer id){
        commentsService.deleteCommentsById(id);
        return "redirect:/blog/comment/comments";
    }
}
