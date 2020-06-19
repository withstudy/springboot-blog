package com.xhb.blog.service.impl;

import com.xhb.blog.entity.Comments;
import com.xhb.blog.entity.Page;
import com.xhb.blog.mapper.CommentsMapper;
import com.xhb.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<Comments> findCommentsAll(Integer con_id,String where) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("con_id",con_id);
        if(where != null){
            params.put("where","%"+where+"%");
        }else{
            params.put("where",null);
        }
        return commentsMapper.findCommentsAll(params);
    }

    @Override
    public List<Comments> findCommentsPage(Page page) {
        Page nPage=new Page();
        nPage.setCurrPage(page.getCurrPage());
        nPage.setTotalCount(page.getTotalCount());
        nPage.setTotalPage();
        nPage.setStart();
        nPage.setWhere(page.getWhere());
        if(page.getWhere2() != null){
            nPage.setWhere2("%"+page.getWhere2()+"%");
        }else{
            nPage.setWhere2(null);
        }
        return commentsMapper.findCommentsPage(nPage);
    }

    @Override
    public Comments findCommentsById(Integer id) {
        return commentsMapper.findCommentsById(id);
    }

    @Override
    @Transactional
    public void deleteCommentsById(Integer id) {
        commentsMapper.deleteCommentsById(id);
    }

    @Override
    @Transactional
    public void addComments(Comments comments) {
        commentsMapper.addComments(comments);
    }

    @Override
    @Transactional
    public void updateComments(Comments comments) {
        commentsMapper.updateComments(comments);
    }

    @Override
    @Transactional
    public void deleteCommentsByCid(Integer cid) {
        commentsMapper.deleteCommentsByCid(cid);
    }
}
