package com.example.ecommerce.controller;

import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
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

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
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
        if(category.isEmpty()) throw new NotFoundException("Category does not exist");
        return ResponseEntity.ok(category);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody Category category){
        Optional<Category> existingCategory = categoryRepository.findById(id);
         if(existingCategory.isEmpty()) throw new NotFoundException("Category does not exist");
        Category updatedCategory = categoryService.updateCategory(existingCategory.get(),category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String id){
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isEmpty()) throw new NotFoundException("Category does not exist");
        categoryService.deleteCategory(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
