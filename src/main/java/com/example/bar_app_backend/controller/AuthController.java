// src/main/java/com/example/bar_app_backend/controller/AuthController.java
package com.example.bar_app_backend.controller;

import com.example.bar_app_backend.model.User;
import com.example.bar_app_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    record RegisterRequest(String name, String email, String password) {}

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request.name(), request.email(), request.password());
            return ResponseEntity.ok(Collections.singletonMap("message", "Utilisateur enregistré avec succès."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    record LoginRequest(String email, String password) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return userService.loginUser(request.email(), request.password())
                .map(user -> ResponseEntity.ok(Map.of(
                                "message", "Connexion réussie",
                                "user", Map.of(
                                        "id", user.getUserId(), // <--- CORRECTED: Use getUserId() here
                                        "email", user.getEmail(),
                                        "name", user.getName(),
                                        "role", user.getRole()
                                )
                        )))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Identifiants invalides.")));
    }
}