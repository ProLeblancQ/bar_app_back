// src/main/java/com/example/bar_app_backend/dto/OrderItemRequestDTO.java
package com.example.bar_app_backend.dto; // Ajusté

public class OrderItemRequestDTO {
    private Long cocktailId;
    private Long sizeId;

    // Constructeur par défaut (nécessaire pour la désérialisation JSON)
    public OrderItemRequestDTO() {}

    // Getters and Setters
    public Long getCocktailId() { return cocktailId; }
    public void setCocktailId(Long cocktailId) { this.cocktailId = cocktailId; }
    public Long getSizeId() { return sizeId; }
    public void setSizeId(Long sizeId) { this.sizeId = sizeId; }
}