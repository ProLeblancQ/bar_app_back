package com.example.bar_app_backend.model;

import jakarta.persistence.*;
import java.io.Serializable; // Gardez cet import si nécessaire

@Entity
@Table(name = "cocktail_ingredients")
public class CocktailIngredient {

    @EmbeddedId
    private CocktailIngredientId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cocktailId") // Mappe le champ 'cocktailId' de l'EmbeddedId
    @JoinColumn(name = "cocktail_id", insertable = false, updatable = false)
    private Cocktail cocktail;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId") // Mappe le champ 'ingredientId' de l'EmbeddedId
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    private Ingredient ingredient; // Relation vers l'entité Ingredient

    @Column(name = "quantity")
    private String quantity;

    public CocktailIngredient() {
        this.id = new CocktailIngredientId();
    }

    public CocktailIngredient(Cocktail cocktail, Ingredient ingredient, String quantity) {
        this.cocktail = cocktail;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.id = new CocktailIngredientId(cocktail.getId(), ingredient.getId());
    }

    public CocktailIngredientId getId() { return id; }
    public void setId(CocktailIngredientId id) { this.id = id; }

    public Cocktail getCocktail() { return cocktail; }
    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
        if (this.id == null) this.id = new CocktailIngredientId();
        if (cocktail != null) this.id.setCocktailId(cocktail.getId());
    }

    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        if (this.id == null) this.id = new CocktailIngredientId();
        if (ingredient != null) this.id.setIngredientId(ingredient.getId());
    }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
}