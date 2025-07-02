package com.example.bar_app_backend.repository;

import com.example.bar_app_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
