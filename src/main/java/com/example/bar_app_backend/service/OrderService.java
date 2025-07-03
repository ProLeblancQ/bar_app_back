// src/main/java/com/example/bar_app_backend/service/OrderService.java
package com.example.bar_app_backend.service;

import com.example.bar_app_backend.dto.OrderRequestDTO;
import com.example.bar_app_backend.dto.OrderItemRequestDTO;
import com.example.bar_app_backend.model.Order;
import com.example.bar_app_backend.model.OrderItem;
import com.example.bar_app_backend.model.User; // Importe l'entité User
import com.example.bar_app_backend.repository.OrderRepository;
import com.example.bar_app_backend.repository.UserRepository; // Importe UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository; // <-- Ajoute ton UserRepository

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) { // <-- Injecte UserRepository
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();

        // Récupère l'objet User complet en utilisant le userId fourni dans le DTO
        User user = userRepository.findById(orderRequestDTO.getUserId())
                                  .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderRequestDTO.getUserId()));
        order.setUser(user); // <-- Assigne l'objet User récupéré à la commande

        order.setStatus("en attente");
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = orderRequestDTO.getItems().stream()
                .map(itemDto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setCocktailId(itemDto.getCocktailId());
                    orderItem.setSizeId(itemDto.getSizeId());
                    orderItem.setPreparationStatus("en attente");
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }
}