package com.xhb.blog.mapper;

import com.xhb.blog.entity.Comments;
import com.xhb.blog.entity.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "commentsMapper")
public interface CommentsMapper {
    public List<Comments> findCommentsAll(Map<String,Object> params);

    public List<Comments> findCommentsPage(Page page);

    public Comments findCommentsById(Integer id);

    public void deleteCommentsById(Integer id);

    public void addComments(Comments comments);

    public void updateComments(Comments comments);

    public List<Comments> findCommentsByCid();

    public void deleteCommentsByCid(Integer cid);
}
