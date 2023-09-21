package com.exam.exam_portal.service;

import java.util.Set;

import com.exam.exam_portal.entity.category.Category;

public interface CategoryService {

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Set<Category> getCategories();

    public Category getCategory(Long categoryID);

    public void deleteCategory(Long categoryID);
}
