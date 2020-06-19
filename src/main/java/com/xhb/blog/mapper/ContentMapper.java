package com.xhb.blog.mapper;

import com.xhb.blog.entity.Content;
import com.xhb.blog.entity.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "contentMapper")
public interface ContentMapper {
    public List<Content> findContentAll(String where);

    public Content findContentById(Integer id);

    public List<Content> findContentByPage(Page page);

    public void deleteContentById(Integer id);

    public void addContent(Content content);

    public void updateContent(Content content);
}
