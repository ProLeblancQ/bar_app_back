package com.example.bar_app_backend.service;

import com.example.bar_app_backend.model.User;
import com.example.bar_app_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // <-- NOUVEAU: Importez BCryptPasswordEncoder

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // <-- NOUVEAU: Déclarez l'encodeur de mot de passe

    // NOUVEAU: Le constructeur doit prendre BCryptPasswordEncoder en paramètre
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // Initialisez l'encodeur
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // --- NOUVEAU: Méthode pour l'inscription (Register) ---
    public User registerUser(String name, String email, String password) {
        // Vérifiez si l'email existe déjà
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email déjà enregistré.");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("user"); // Donnez un rôle par défaut

        return userRepository.save(newUser);
    }

    // --- NOUVEAU: Méthode pour la connexion (Login) ---
    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Comparez le mot de passe fourni avec le mot de passe haché stocké
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user); // Mots de passe correspondent
            }
        }
        return Optional.empty(); 
    }
}