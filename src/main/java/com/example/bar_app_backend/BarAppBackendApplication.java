package com.example.bar_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Supprimez les imports ci-dessous car le Bean sera dans SecurityConfig
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BarAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarAppBackendApplication.class, args);
    }

    // Supprimez cette méthode de Bean, elle est maintenant dans SecurityConfig.java
    // @Bean
    // public BCryptPasswordEncoder bCryptPasswordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
}