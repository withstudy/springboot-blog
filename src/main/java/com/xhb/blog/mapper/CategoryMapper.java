package com.xhb.blog.mapper;

import com.xhb.blog.entity.Category;
import com.xhb.blog.entity.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "categoryMapper")
public interface CategoryMapper {
    public List<Category> findCategoryAll();

    public List<Category> findCategoryPage(Page<Category> page);

    public void deleteCategoryById(Integer id);

    public void addCategory(Category category);

    public Category findCategoryById(Integer id);

    public void updateCategory(Category category);

    public List<Category> findCategoryByName(String name);
}
