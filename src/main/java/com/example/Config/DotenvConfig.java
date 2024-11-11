package com.example.Config;

import org.springframework.context.annotation.Bean;
<<<<<<< Updated upstream

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig {
    @Bean
    public Dotenv dotenv() {
        // Tải và nạp file .env vào môi trường
        return Dotenv.load();
    }
}
=======
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvConfig {
    @Bean
    public Dotenv dotenv() {
        Dotenv dotenv = Dotenv.load();

        System.out.println("Google Client ID: " + dotenv.get("GOOGLE_CLIENT_ID"));
        System.out.println("Google Client Secret: " + dotenv.get("GOOGLE_CLIENT_SECRET"));
        return dotenv;
    }
}
>>>>>>> Stashed changes
