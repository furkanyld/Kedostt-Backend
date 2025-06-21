package com.kedostt_backend.Kedostt_Backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Kedostt API")
                        .version("1.0")
                        .description("Hayvan sahiplendirme ve bağış sistemi için API dokümantasyonu"));
    }
}
