package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getCategories();
    Optional<Category> getCategory(String id);
    Category updateCategory(Category existingCategory,Category category
    );
    void deleteCategory(String id);
}
