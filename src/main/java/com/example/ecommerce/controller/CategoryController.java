package com.example.ecommerce.controller;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
   private  CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
      Category category1= categoryService.createCategory(category);
      return ResponseEntity.ok(category1);
    }
}
