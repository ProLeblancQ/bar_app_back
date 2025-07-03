package com.example.bar_app_backend.controller;

import com.example.bar_app_backend.service.CocktailService;
import com.example.bar_app_backend.dto.CocktailDTO; // Utilise le DTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService service) {
        this.cocktailService = service;
    }

    @GetMapping("/cocktails")
    public List<CocktailDTO> getAllCocktails(@RequestParam(required = false) Integer categoryId) {
        if (categoryId != null) {
            return cocktailService.getCocktailsByCategoryId(categoryId);
        } else {
            return cocktailService.getAllCocktails();
        }
    }

    @GetMapping("/cocktails/{id}")
    public ResponseEntity<CocktailDTO> getCocktailById(@PathVariable Long id) {
        Optional<CocktailDTO> cocktail = cocktailService.getCocktailById(id);
        return cocktail.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- NEW: Endpoint to create a new cocktail ---
    @PostMapping("/cocktails") // Handles POST requests to /api/cocktails
    public ResponseEntity<CocktailDTO> createCocktail(@RequestBody CocktailDTO cocktailDTO) {
        // The service handles the persistence logic and returns the DTO of the created cocktail
        CocktailDTO createdCocktail = cocktailService.createCocktail(cocktailDTO);
        // Return the created cocktail with a 201 Created status
        return new ResponseEntity<>(createdCocktail, HttpStatus.CREATED);
    }
    // --- END NEW SECTION ---

    @PutMapping("/cocktails/{id}")
    public ResponseEntity<CocktailDTO> updateCocktail(@PathVariable Long id, @RequestBody CocktailDTO updatedCocktailDTO) {
        CocktailDTO result = cocktailService.updateCocktail(id, updatedCocktailDTO);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cocktails/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Long id) {
        try {
            cocktailService.deleteCocktail(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du cocktail avec l'ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}