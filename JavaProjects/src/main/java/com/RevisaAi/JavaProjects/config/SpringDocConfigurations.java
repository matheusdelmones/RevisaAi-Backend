package com.RevisaAi.JavaProjects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {


    @Bean
    public OpenAPI CustomOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                    .addSecuritySchemes("bearer-key",
                        new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")))
                            
                .info(new Info()
                    .title("Projeto RevisaAi")
                    .version("1.0")
                    .description("API Rest de Aplicação de estudos, com CRUD de Topicos e Revisões"));
    }
    
}
