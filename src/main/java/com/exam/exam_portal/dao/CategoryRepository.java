package com.exam.exam_portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam_portal.entity.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
