package com.example.bar_app_backend.service;

import com.example.bar_app_backend.model.Cocktail;
import com.example.bar_app_backend.repository.CocktailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository repository) {
        this.cocktailRepository = repository;
    }

    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    // Cette méthode utilise le repository pour le filtrage
    public List<Cocktail> getCocktailsByCategoryId(Integer categoryId) {
        return cocktailRepository.findByCategoryId(categoryId);
    }
}