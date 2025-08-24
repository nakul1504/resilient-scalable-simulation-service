package com.simulation.service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customSwaggerConfig(){
        return new OpenAPI().info(
                new Info().title("Simulation Service")
                        .description("By Nakul Mathur")
        ).servers(List.of(new Server().url("http://localhost:8080").description("Local"),
                new Server().url("http://localhost:8081").description("Production Demo")));

//                TODO: to implement security in the application
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(new Components().addSecuritySchemes
//                        ("bearerAuth", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP).
//                                scheme("bearer").
//                                bearerFormat("JWT").
//                                in(SecurityScheme.In.HEADER).
//                                name("Authorization")));

    }
}
