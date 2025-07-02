package com.example.bar_app_backend.service;

import com.example.bar_app_backend.model.Category;
import com.example.bar_app_backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
