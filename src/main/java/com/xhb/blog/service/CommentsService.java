package com.xhb.blog.service;

import com.xhb.blog.entity.Comments;
import com.xhb.blog.entity.Page;

import java.util.List;

public interface CommentsService {
    public List<Comments> findCommentsAll(Integer con_id,String where);

    public List<Comments> findCommentsPage(Page page);

    public Comments findCommentsById(Integer id);

    public void deleteCommentsById(Integer id);

    public void addComments(Comments comments);

    public void updateComments(Comments comments);

    public void deleteCommentsByCid(Integer cid);
}
