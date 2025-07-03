package com.example.bar_app_backend.controller;

import com.example.bar_app_backend.model.Cocktail;
import com.example.bar_app_backend.service.CocktailService;
import org.springframework.http.HttpStatus; // Importe HttpStatus
import org.springframework.http.ResponseEntity; // Importe ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Importe Optional

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

    // Nouvelle méthode pour récupérer un cocktail par ID (optionnel, mais bonne pratique)
    @GetMapping("/cocktails/{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable Long id) {
        Optional<Cocktail> cocktail = cocktailService.getCocktailById(id);
        return cocktail.map(ResponseEntity::ok) // Si trouvé, retourne 200 OK avec le cocktail
                       .orElseGet(() -> ResponseEntity.notFound().build()); // Sinon, retourne 404 Not Found
    }

    // Nouvelle méthode pour mettre à jour un cocktail (PUT)
    @PutMapping("/cocktails/{id}")
    public ResponseEntity<Cocktail> updateCocktail(@PathVariable Long id, @RequestBody Cocktail updatedCocktail) {
        Cocktail result = cocktailService.updateCocktail(id, updatedCocktail);
        if (result != null) {
            return ResponseEntity.ok(result); // Retourne 200 OK avec le cocktail mis à jour
        } else {
            return ResponseEntity.notFound().build(); // Retourne 404 Not Found si le cocktail n'existe pas
        }
    }

    // Nouvelle méthode pour supprimer un cocktail (DELETE)
    @DeleteMapping("/cocktails/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Long id) {
        try {
            cocktailService.deleteCocktail(id);
            return ResponseEntity.noContent().build(); // Retourne 204 No Content en cas de succès
        } catch (Exception e) {
            // En cas d'erreur (par exemple, ID non trouvé), tu peux retourner 404 ou 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}