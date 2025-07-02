package com.example.bar_app_backend.controller;

import com.example.bar_app_backend.model.Cocktail;
import com.example.bar_app_backend.service.CocktailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService service) {
        this.cocktailService = service;
    }

    @GetMapping("/cocktails")
    public List<Cocktail> getAllCocktails(@RequestParam(required = false) Integer categoryId) {
        if (categoryId != null) {
            return cocktailService.getCocktailsByCategoryId(categoryId);
        } else {
            return cocktailService.getAllCocktails();
        }
    }
}