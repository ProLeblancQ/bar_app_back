package com.example.bar_app_backend.service;

import com.example.bar_app_backend.model.Cocktail;
import com.example.bar_app_backend.repository.CocktailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Importe Optional pour findById

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository repository) {
        this.cocktailRepository = repository;
    }

    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    public List<Cocktail> getCocktailsByCategoryId(Integer categoryId) {
        return cocktailRepository.findByCategoryId(categoryId);
    }

    // Nouvelle méthode pour trouver un cocktail par ID
    public Optional<Cocktail> getCocktailById(Long id) {
        return cocktailRepository.findById(id);
    }

    // Nouvelle méthode pour mettre à jour un cocktail
    public Cocktail updateCocktail(Long id, Cocktail updatedCocktail) {
        // Vérifie si le cocktail existe
        Optional<Cocktail> existingCocktail = cocktailRepository.findById(id);

        if (existingCocktail.isPresent()) {
            // Met à jour les champs du cocktail existant avec les nouvelles données
            Cocktail cocktailToUpdate = existingCocktail.get();
            cocktailToUpdate.setName(updatedCocktail.getName());
            cocktailToUpdate.setDescription(updatedCocktail.getDescription());
            cocktailToUpdate.setImageUrl(updatedCocktail.getImageUrl());
            cocktailToUpdate.setCategoryId(updatedCocktail.getCategoryId());

            // Sauvegarde et retourne le cocktail mis à jour
            return cocktailRepository.save(cocktailToUpdate);
        } else {
            // Gérer le cas où le cocktail n'est pas trouvé
            // Tu peux lancer une exception ou retourner null/Optional.empty()
            // Pour cet exemple, nous allons simplement retourner null,
            // mais une meilleure pratique serait une exception personnalisée ou un ResponseEntity.notFound() dans le contrôleur.
            return null;
        }
    }

    // Nouvelle méthode pour supprimer un cocktail
    public void deleteCocktail(Long id) {
        cocktailRepository.deleteById(id);
    }
}