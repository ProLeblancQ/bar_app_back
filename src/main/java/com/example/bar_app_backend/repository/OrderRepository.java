// src/main/java/com/example/bar_app_backend/repository/OrderRepository.java
package com.example.bar_app_backend.repository; // Ajusté

import com.example.bar_app_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}