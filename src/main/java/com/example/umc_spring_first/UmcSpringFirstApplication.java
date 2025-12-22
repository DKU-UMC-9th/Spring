package com.example.umc_spring_first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class UmcSpringFirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmcSpringFirstApplication.class, args);
    }
}
