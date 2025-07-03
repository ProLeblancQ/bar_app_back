package com.example.bar_app_backend.dto;

public class CocktailDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer categoryId;
    private Double price; // Ce champ contiendra le prix pour la taille par défaut

    public CocktailDTO() {}

    public CocktailDTO(Long id, String name, String description, String imageUrl, Integer categoryId, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.price = price;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}