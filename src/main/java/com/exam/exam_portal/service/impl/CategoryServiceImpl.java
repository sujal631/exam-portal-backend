package com.exam.exam_portal.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exam_portal.dao.CategoryRepository;
import com.exam.exam_portal.entity.category.Category;
import com.exam.exam_portal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long categoryID) {
        return this.categoryRepository.findById(categoryID).get();
    }

    @Override
    public void deleteCategory(Long categoryID) {
        this.categoryRepository.deleteById(categoryID);
    }

}
