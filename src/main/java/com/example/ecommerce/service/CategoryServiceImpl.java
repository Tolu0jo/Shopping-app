package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements  CategoryService {

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
    public Category updateCategory(String id, Category category) {
        Optional<Category> category1 = getCategory(id);
        if (category1.isPresent()) {
          Category existingCategory= category1.get();
          existingCategory.setCategoryName(category.getCategoryName());
          existingCategory.setDescription(category.getDescription());
          existingCategory.setImageUrl(category.getImageUrl());
        return categoryRepository.save(existingCategory);
        }
       return null;
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}