package com.example.bar_app_backend.repository;

import com.example.bar_app_backend.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}
