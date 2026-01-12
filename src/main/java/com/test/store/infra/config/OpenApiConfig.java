package com.test.store.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderManagementOpenAPI(@Value("${server.port:8080}") String serverPort) {
        Server localServer = new Server()
                .url("http://localhost:" + serverPort)
                .description("Local Development Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Store API")
                        .version("1.0.0")
                        .description("API for managing store prices")
                ).servers(List.of(localServer));
    }
}
