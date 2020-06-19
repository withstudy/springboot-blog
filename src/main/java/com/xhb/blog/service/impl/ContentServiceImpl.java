package com.xhb.blog.service.impl;

import com.xhb.blog.entity.Content;
import com.xhb.blog.entity.Page;
import com.xhb.blog.mapper.ContentMapper;
import com.xhb.blog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<Content> findContentAll(String where) {
        String nWhere=null;
        if(null != where){
            nWhere= "%"+where+"%";
        }
        return contentMapper.findContentAll(nWhere);
    }

    @Override
    public Content findContentById(Integer id) {
        return contentMapper.findContentById(id);
    }

    @Override
    public List<Content> findContentByPage(Page page) {
        Page nPage=new Page();
        nPage.setCurrPage(page.getCurrPage());
        nPage.setTotalCount(page.getTotalCount());
        nPage.setTotalPage();
        nPage.setStart();
        if(null != page.getWhere2()){
            nPage.setWhere2("%"+page.getWhere2()+"%");
        }else{
            nPage.setWhere2(null);
        }
        return contentMapper.findContentByPage(nPage);
    }

    @Override
    @Transactional
    public void deleteContentById(Integer id) {
        contentMapper.deleteContentById(id);
    }

    @Override
    @Transactional
    public void addContent(Content content) {
        contentMapper.addContent(content);
    }

    @Override
    @Transactional
    public void updateContent(Content content) {
        contentMapper.updateContent(content);
    }
}
