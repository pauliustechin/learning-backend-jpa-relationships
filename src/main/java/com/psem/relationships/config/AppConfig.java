package com.psem.relationships.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Create modelMapper bean to convert DTO's to models and vice verca.
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
