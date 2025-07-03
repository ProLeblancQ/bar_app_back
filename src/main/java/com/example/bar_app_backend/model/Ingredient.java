package com.example.bar_app_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients") // Assurez-vous que le nom de la table est correct
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Integer id;

    @Column(name = "name")
    private String name; // Ex: "Sucre", "Citron", "Rhum"

    public Ingredient() {}

    public Ingredient(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}