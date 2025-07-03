package com.example.bar_app_backend.repository;

import com.example.bar_app_backend.model.CocktailIngredient;
import com.example.bar_app_backend.model.CocktailIngredientId; // Importe la classe de clé composite
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, CocktailIngredientId> {
    // Méthode pour trouver tous les ingrédients associés à un cocktail spécifique
    List<CocktailIngredient> findByIdCocktailId(Long cocktailId);
    // Méthode pour supprimer tous les ingrédients associés à un cocktail (si la cascade n'était pas utilisée)
    void deleteByIdCocktailId(Long cocktailId);
}