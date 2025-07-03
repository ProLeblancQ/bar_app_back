// src/main/java/com/example/bar_app_backend/controller/OrderController.java
package com.example.bar_app_backend.controller; // Ajusté

import com.example.bar_app_backend.dto.OrderRequestDTO; // Ajusté
import com.example.bar_app_backend.model.Order;
import com.example.bar_app_backend.service.OrderService; // Ajusté
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            Order newOrder = orderService.createOrder(orderRequestDTO);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log l'erreur pour le débogage (utilise un logger comme SLF4J/Logback en prod)
            System.err.println("Erreur lors de la création de la commande : " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}