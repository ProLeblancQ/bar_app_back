package com.example.bar_app_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CocktailIngredientId implements Serializable {
    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Column(name = "ingredient_id")
    private Integer ingredientId;

    public CocktailIngredientId() {}

    public CocktailIngredientId(Long cocktailId, Integer ingredientId) {
        this.cocktailId = cocktailId;
        this.ingredientId = ingredientId;
    }

    public Long getCocktailId() { return cocktailId; }
    public void setCocktailId(Long cocktailId) { this.cocktailId = cocktailId; }
    public Integer getIngredientId() { return ingredientId; }
    public void setIngredientId(Integer ingredientId) { this.ingredientId = ingredientId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailIngredientId that = (CocktailIngredientId) o;
        return Objects.equals(cocktailId, that.cocktailId) &&
               Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, ingredientId);
    }
}