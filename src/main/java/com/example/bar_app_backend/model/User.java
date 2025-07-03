// src/main/java/com/example/bar_app_backend/model/User.java
package com.example.bar_app_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Assure-toi que le nom de la table est bien "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // <-- AJOUTE ou MODIFIE cette ligne
    private Long userId; // <-- CHANGE le nom du champ en 'userId'

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    // Constructeurs
    public User() {}

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters et Setters pour userId
    public Long getUserId() { // <-- CHANGE le nom de la méthode
        return this.userId;
    }

    public void setUserId(Long userId) { // <-- CHANGE le nom de la méthode et du paramètre
        this.userId = userId;
    }

    // Getters et Setters restants (ils étaient corrects pour les autres champs)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}