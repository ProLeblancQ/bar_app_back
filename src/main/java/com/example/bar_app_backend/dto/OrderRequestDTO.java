// src/main/java/com/example/bar_app_backend/dto/OrderRequestDTO.java
package com.example.bar_app_backend.dto; // Ajusté

import java.util.List;

public class OrderRequestDTO {
    private Long userId;
    private List<OrderItemRequestDTO> items;

    // Constructeur par défaut
    public OrderRequestDTO() {}

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<OrderItemRequestDTO> getItems() { return items; }
    public void setItems(List<OrderItemRequestDTO> items) { this.items = items; }
}