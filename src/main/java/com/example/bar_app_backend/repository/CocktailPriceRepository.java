package com.example.bar_app_backend.repository;

import com.example.bar_app_backend.model.CocktailPrice;
// CORRECTION: Ajouter l'import pour CocktailPriceId
import com.example.bar_app_backend.model.CocktailPriceId; // NOUVEL IMPORT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// CORRECTION: Utiliser directement CocktailPriceId comme type de l'ID
public interface CocktailPriceRepository extends JpaRepository<CocktailPrice, CocktailPriceId> {
    // Trouve tous les prix pour un ID de cocktail donné
    List<CocktailPrice> findByIdCocktailId(Long cocktailId);
    // Trouve un prix spécifique par ID de cocktail et ID de taille
    Optional<CocktailPrice> findByIdCocktailIdAndIdSizeId(Long cocktailId, Integer sizeId);
    // Supprime tous les prix pour un ID de cocktail donné (si la cascade n'était pas utilisée)
    void deleteByIdCocktailId(Long cocktailId);
}