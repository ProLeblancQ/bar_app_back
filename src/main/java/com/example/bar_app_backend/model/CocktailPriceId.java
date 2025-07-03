package com.example.bar_app_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// CORRECTION: Cette classe est maintenant un fichier autonome et doit être public.
// Elle ne doit PAS être static ici, car elle n'est plus une classe interne.
@Embeddable
public class CocktailPriceId implements Serializable {
    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Column(name = "size_id")
    private Integer sizeId;

    public CocktailPriceId() {}

    public CocktailPriceId(Long cocktailId, Integer sizeId) {
        this.cocktailId = cocktailId;
        this.sizeId = sizeId;
    }

    // Getters, Setters, hashCode et equals sont indispensables pour les clés composites
    public Long getCocktailId() { return cocktailId; }
    public void setCocktailId(Long cocktailId) { this.cocktailId = cocktailId; }
    public Integer getSizeId() { return sizeId; }
    public void setSizeId(Integer sizeId) { this.sizeId = sizeId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailPriceId that = (CocktailPriceId) o;
        return Objects.equals(cocktailId, that.cocktailId) &&
               Objects.equals(sizeId, that.sizeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, sizeId);
    }
}