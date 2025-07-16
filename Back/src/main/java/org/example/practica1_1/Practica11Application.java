package org.example.practica1_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry

@SpringBootApplication

public class Practica11Application {
    public static void main(String[] args) {
        SpringApplication.run(Practica11Application.class, args);
    }
}

