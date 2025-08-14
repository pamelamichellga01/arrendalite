package com.arrendalite.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Arrenda Lite API")
                        .description("API para el sistema de arriendo de propiedades temporales con validaciones de fechas, descuentos y cancelación de reservas.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Arrenda Lite Team")
                                .email("contacto@arrendalite.com")
                                .url("https://arrendalite.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor de Desarrollo")
                ));
    }
}


