package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvConfig {
    @Bean
    public Dotenv dotenv() {
        // Tải và nạp file .env vào môi trường
        return Dotenv.load();
    }
}
