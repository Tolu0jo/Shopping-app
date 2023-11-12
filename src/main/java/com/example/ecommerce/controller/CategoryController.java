package com.example.ecommerce.controller;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {

   private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
      Category newCategory= categoryService.createCategory(category);
      return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>>listCategories(){
        List<Category> categories= categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable String id){
        Optional<Category> category= categoryService.getCategory(id);
        if(category.isEmpty()) return new ResponseEntity<>(category,HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(category);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(id,category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("delete/{id}")
    public String deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
        return "Deleted Successfully !!!";
    }

}
