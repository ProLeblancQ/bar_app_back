package com.example.bar_app_backend.model;

import jakarta.persistence.*;
import java.io.Serializable; // Gardez cet import si Serializable est utilisé ailleurs
import java.util.Objects; // Gardez cet import si Objects est utilisé ailleurs

// CORRECTION: Import de la classe CocktailPriceId maintenant qu'elle est séparée
import com.example.bar_app_backend.model.CocktailPriceId;


@Entity
@Table(name = "cocktail_prices")
public class CocktailPrice {

    @EmbeddedId // Indique que la clé primaire est une classe imbriquée
    private CocktailPriceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cocktailId") // Mappe le champ 'cocktailId' de l'EmbeddedId vers cette relation
    @JoinColumn(name = "cocktail_id", insertable = false, updatable = false) // Évite les insertions/mises à jour en double de la colonne
    private Cocktail cocktail;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sizeId") // Mappe le champ 'sizeId' de l'EmbeddedId vers cette relation
    @JoinColumn(name = "size_id", insertable = false, updatable = false) // Évite les insertions/mises à jour en double de la colonne
    private Size size;

    @Column(name = "price")
    private Double price;

    public CocktailPrice() {
        this.id = new CocktailPriceId(); // Initialise l'ID embarqué
    }

    public CocktailPrice(Cocktail cocktail, Size size, Double price) {
        this.cocktail = cocktail;
        this.size = size;
        this.price = price;
        this.id = new CocktailPriceId(cocktail.getId(), size.getId()); // Crée l'ID composite
    }

    public CocktailPriceId getId() { return id; }
    public void setId(CocktailPriceId id) { this.id = id; }

    public Cocktail getCocktail() { return cocktail; }
    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
        if (this.id == null) this.id = new CocktailPriceId();
        if (cocktail != null) this.id.setCocktailId(cocktail.getId());
    }

    public Size getSize() { return size; }
    public void setSize(Size size) {
        this.size = size;
        if (this.id == null) this.id = new CocktailPriceId();
        if (size != null) this.id.setSizeId(size.getId());
    }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}