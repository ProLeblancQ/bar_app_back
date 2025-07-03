package com.example.bar_app_backend.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cocktails")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "category_id")
    private Integer categoryId;

    // Relation One-to-Many avec CocktailPrice (déjà existante)
    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CocktailPrice> prices = new HashSet<>();

    // NOUVEAU : Relation One-to-Many avec CocktailIngredient
    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CocktailIngredient> ingredients = new HashSet<>(); // IMPORTANT: Initialiser la collection

    public Cocktail() {}

    public Cocktail(String name, String description, String imageUrl, Integer categoryId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public Set<CocktailPrice> getPrices() { return prices; }
    public void setPrices(Set<CocktailPrice> prices) { this.prices = prices; }

    public void addPrice(CocktailPrice price) {
        prices.add(price);
        price.setCocktail(this);
    }

    public void removePrice(CocktailPrice price) {
        prices.remove(price);
        price.setCocktail(null);
    }

    // NOUVEAU : Getters/Setters et méthodes utilitaires pour les ingrédients
    public Set<CocktailIngredient> getIngredients() { return ingredients; }
    public void setIngredients(Set<CocktailIngredient> ingredients) { this.ingredients = ingredients; }

    public void addIngredient(CocktailIngredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setCocktail(this);
    }

    public void removeIngredient(CocktailIngredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.setCocktail(null);
    }
}