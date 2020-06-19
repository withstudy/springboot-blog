package com.xhb.blog.service;

import com.xhb.blog.entity.Content;
import com.xhb.blog.entity.Page;

import java.util.List;

public interface ContentService {
    public List<Content> findContentAll(String where);

    public Content findContentById(Integer id);

    public List<Content> findContentByPage(Page page);

    public void deleteContentById(Integer id);

    public void addContent(Content content);

    public void updateContent(Content content);
}
