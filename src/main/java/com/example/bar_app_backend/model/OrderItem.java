// src/main/java/com/example/bar_app_backend/model/OrderItem.java
package com.example.bar_app_backend.model; // Ajusté

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    // Relation ManyToOne avec Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // Colonne de la clé étrangère dans la table order_items
    private Order order;

    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "preparation_status")
    private String preparationStatus;

    // Constructeur par défaut (nécessaire pour JPA)
    public OrderItem() {}

    // Getters and Setters
    public Long getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Long orderItemId) { this.orderItemId = orderItemId; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    public Long getCocktailId() { return cocktailId; }
    public void setCocktailId(Long cocktailId) { this.cocktailId = cocktailId; }
    public Long getSizeId() { return sizeId; }
    public void setSizeId(Long sizeId) { this.sizeId = sizeId; }
    public String getPreparationStatus() { return preparationStatus; }
    public void setPreparationStatus(String preparationStatus) { this.preparationStatus = preparationStatus; }
}