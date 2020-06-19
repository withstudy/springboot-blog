package com.xhb.blog.service.impl;

import com.xhb.blog.entity.Category;
import com.xhb.blog.entity.Page;
import com.xhb.blog.mapper.CategoryMapper;
import com.xhb.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryAll() {
        return categoryMapper.findCategoryAll();
    }

    @Override
    public List<Category> findCategoryPage(Page<Category> page) {
        return categoryMapper.findCategoryPage(page);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer id) {
        categoryMapper.deleteCategoryById(id);
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryMapper.findCategoryById(id);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public List<Category> findCategoryByName(String name) {
        return categoryMapper.findCategoryByName(name);
    }
}
