package com.example.bar_app_backend.repository;

import com.example.bar_app_backend.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    List<Cocktail> findByCategoryId(Integer categoryId);
}