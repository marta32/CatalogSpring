package com.example.springbootcatalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
public class SpringbootCatalogApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCatalogApplication.class, args);

    }
}