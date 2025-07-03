package com.example.bar_app_backend.service;

import com.example.bar_app_backend.model.Cocktail;
import com.example.bar_app_backend.model.CocktailPrice;
import com.example.bar_app_backend.model.Size;
import com.example.bar_app_backend.repository.CocktailRepository;
import com.example.bar_app_backend.repository.CocktailPriceRepository;
import com.example.bar_app_backend.repository.SizeRepository;
import com.example.bar_app_backend.dto.CocktailDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.bar_app_backend.model.CocktailPriceId;
import com.example.bar_app_backend.repository.CocktailIngredientRepository; // Assurez-vous que cet import est là

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailPriceRepository cocktailPriceRepository;
    private final SizeRepository sizeRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;

    public CocktailService(CocktailRepository cocktailRepository,
                           CocktailPriceRepository cocktailPriceRepository,
                           SizeRepository sizeRepository,
                           CocktailIngredientRepository cocktailIngredientRepository) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailPriceRepository = cocktailPriceRepository;
        this.sizeRepository = sizeRepository;
        this.cocktailIngredientRepository = cocktailIngredientRepository;
    }

    private CocktailDTO convertToDto(Cocktail cocktail) {
        Double price = null;
        List<CocktailPrice> prices = cocktailPriceRepository.findByIdCocktailId(cocktail.getId());
        if (!prices.isEmpty()) {
            Optional<CocktailPrice> defaultSizePrice = prices.stream()
                    .filter(cp -> cp.getSize() != null && cp.getSize().getId() != null && cp.getSize().getId().equals(1))
                    .findFirst();
            if (defaultSizePrice.isPresent()) {
                price = defaultSizePrice.get().getPrice();
            } else {
                price = prices.get(0).getPrice();
            }
        }

        return new CocktailDTO(
                cocktail.getId(),
                cocktail.getName(),
                cocktail.getDescription(),
                cocktail.getImageUrl(),
                cocktail.getCategoryId(),
                price
        );
    }

    // NOUVEAU : Méthode pour créer un cocktail
    @Transactional
    public CocktailDTO createCocktail(CocktailDTO cocktailDTO) {
        // Crée une nouvelle instance de l'entité Cocktail à partir du DTO
        Cocktail newCocktail = new Cocktail();
        newCocktail.setName(cocktailDTO.getName());
        newCocktail.setDescription(cocktailDTO.getDescription());
        newCocktail.setImageUrl(cocktailDTO.getImageUrl());
        newCocktail.setCategoryId(cocktailDTO.getCategoryId());

        // Sauvegarde le nouveau cocktail. L'ID sera généré ici.
        Cocktail savedCocktail = cocktailRepository.save(newCocktail);

        // Si un prix est fourni dans le DTO, le sauvegarder également
        if (cocktailDTO.getPrice() != null) {
            Integer defaultSizeId = 10; // Ou un ID de taille par défaut approprié
            Optional<Size> defaultSizeOpt = sizeRepository.findById(defaultSizeId);

            if (defaultSizeOpt.isPresent()) {
                Size defaultSize = defaultSizeOpt.get();
                CocktailPrice priceToSave = new CocktailPrice();
                priceToSave.setCocktail(savedCocktail); // Associe le cocktail fraîchement créé
                priceToSave.setSize(defaultSize);
                priceToSave.setPrice(cocktailDTO.getPrice());
                // Il est crucial de définir l'ID composite correctement pour les nouvelles entités liées
                priceToSave.setId(new CocktailPriceId(savedCocktail.getId(), defaultSize.getId()));

                cocktailPriceRepository.save(priceToSave);
            } else {
                System.err.println("Taille par défaut (ID 1) non trouvée lors de la création du cocktail.");
                // Optionnel: lancer une exception ou gérer cette erreur selon la logique métier
            }
        }
        // Il est important de retourner le DTO du cocktail sauvegardé, qui inclura l'ID généré par la BDD
        return convertToDto(savedCocktail);
    }


    public List<CocktailDTO> getAllCocktails() {
        return cocktailRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CocktailDTO> getCocktailsByCategoryId(Integer categoryId) {
        return cocktailRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CocktailDTO> getCocktailById(Long id) {
        return cocktailRepository.findById(id)
                .map(this::convertToDto);
    }

    @Transactional
    public CocktailDTO updateCocktail(Long id, CocktailDTO updatedCocktailDTO) {
        Optional<Cocktail> existingCocktailOpt = cocktailRepository.findById(id);

        if (existingCocktailOpt.isPresent()) {
            Cocktail cocktailToUpdate = existingCocktailOpt.get();
            cocktailToUpdate.setName(updatedCocktailDTO.getName());
            cocktailToUpdate.setDescription(updatedCocktailDTO.getDescription());
            cocktailToUpdate.setImageUrl(updatedCocktailDTO.getImageUrl());
            cocktailToUpdate.setCategoryId(updatedCocktailDTO.getCategoryId());

            Cocktail savedCocktail = cocktailRepository.save(cocktailToUpdate);

            if (updatedCocktailDTO.getPrice() != null) {
                Integer defaultSizeId = 10;
                Optional<Size> defaultSizeOpt = sizeRepository.findById(defaultSizeId);

                if (defaultSizeOpt.isPresent()) {
                    Size defaultSize = defaultSizeOpt.get();
                    CocktailPriceId priceId = new CocktailPriceId(savedCocktail.getId(), defaultSize.getId());
                    Optional<CocktailPrice> existingPriceOpt = cocktailPriceRepository.findById(priceId);

                    CocktailPrice priceToSave;
                    if (existingPriceOpt.isPresent()) {
                        priceToSave = existingPriceOpt.get();
                        priceToSave.setPrice(updatedCocktailDTO.getPrice());
                    } else {
                        priceToSave = new CocktailPrice();
                        priceToSave.setCocktail(savedCocktail);
                        priceToSave.setSize(defaultSize);
                        priceToSave.setPrice(updatedCocktailDTO.getPrice());
                        priceToSave.setId(new CocktailPriceId(savedCocktail.getId(), defaultSize.getId()));
                    }
                    cocktailPriceRepository.save(priceToSave);
                } else {
                    System.err.println("Taille par défaut (ID 1) non trouvée. Le prix n'a pas été mis à jour.");
                }
            }

            return convertToDto(savedCocktail);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCocktail(Long id) {
        cocktailRepository.deleteById(id);
    }
}