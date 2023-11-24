package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategory(Category existingCategory, Category category) {
          existingCategory.setCategoryName(category.getCategoryName());
          existingCategory.setDescription(category.getDescription());
          existingCategory.setImageUrl(category.getImageUrl());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}