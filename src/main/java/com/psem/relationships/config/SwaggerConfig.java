package com.psem.relationships.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Create OpenAPI bean to customize swagger documentation.
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("JPA relationships")
                        .description("Project to learn JPA relationships")
                        .contact(new Contact()
                                .name("Paulius Semaska")
                                .email("pauliustechin@gmail.com")
                                .url("https://github.com/pauliustechin")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project on git hub")
                        .url("https://github.com/pauliustechin/learning-backend-jpa-relationships"));
    }
}

