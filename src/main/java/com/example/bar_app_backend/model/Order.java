// src/main/java/com/example/bar_app_backend/model/Order.java
package com.example.bar_app_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    // --- MODIFICATION ESSENTIELLE ICI ---
    @ManyToOne(fetch = FetchType.LAZY) // Une commande appartient à un utilisateur
    // La colonne 'user_id' dans la table 'orders' fera référence à la colonne 'user_id' dans la table 'users'
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user; // <-- Remplace 'Long userId' par l'objet 'User'
    // ------------------------------------

    private String status;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order() {}

    // Getters et Setters mis à jour pour l'objet User
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public User getUser() { return user; } // Nouveau getter
    public void setUser(User user) { this.user = user; } // Nouveau setter

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        if (orderItems != null) {
            orderItems.forEach(item -> item.setOrder(this));
        }
    }
}