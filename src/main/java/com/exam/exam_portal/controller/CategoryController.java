package com.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam_portal.entity.category.Category;
import com.exam.exam_portal.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // add category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category category2 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category2);
    }

    // get all Categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    // get a category
    @GetMapping("/{categoryID}")
    public Category getCategory(@PathVariable("categoryID") Long categoryID) {
        return this.categoryService.getCategory(categoryID);
    }

    // update a category
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    // delete a category
    @DeleteMapping("/{categoryID}")
    public void deleteCategory(@PathVariable("categoryID") Long categoryID) {
        this.categoryService.deleteCategory(categoryID);
    }
}
