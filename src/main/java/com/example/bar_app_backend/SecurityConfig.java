package com.example.bar_app_backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Applique à TOUS les chemins
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // <-- Applique la config CORS en premier
            .csrf(csrf -> csrf.disable()) // Désactive CSRF pour les API REST (recommandé en dev)
            .authorizeHttpRequests(authorize -> authorize
                // Autorise TOUTES les requêtes vers /api/** sans authentification
                .requestMatchers("/api/**").permitAll()
                // Si vous voulez autoriser toutes les autres pages sans authentification pendant le développement
                .anyRequest().permitAll()
                // Si vous voulez que toutes les autres requêtes soient authentifiées (plus tard)
                // .anyRequest().authenticated()
            );
            // Si vous avez un formulaire de login par défaut qui cause des redirections indésirables,
            // assurez-vous qu'il est désactivé ou configuré pour l'API.
            // .formLogin(Customizer.withDefaults()); // <--- Si cette ligne est présente, commentez-la pour l'API
            // .httpBasic(Customizer.withDefaults()); // <--- Si vous n'utilisez pas l'authentification HTTP Basic
            // Pour une API REST purement stateless (sans session)
            // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}