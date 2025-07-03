// src/main/java/com/example/bar_app_backend/repository/OrderItemRepository.java
package com.example.bar_app_backend.repository; // Ajusté

import com.example.bar_app_backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}