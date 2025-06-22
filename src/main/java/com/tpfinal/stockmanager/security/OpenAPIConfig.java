package com.tpfinal.stockmanager.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock Manager API")
                        .version("1.0.0")
                        .description("Documentación de la API para gestión de stock, productos y categorías.")
                );
    }
}

