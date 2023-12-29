package com.accenture.userservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringOpenApiConfig {
  @Bean
  public OpenAPI openApiInformation() {

    Server localServer = new Server().url("http://localhost:8080").description("Localhost Server URL");

    Contact contact = new Contact().email("mayuriJ@gmail.com").name("Mayuri Jadhav");

    Info info = new Info().contact(contact).description("Spring Boot 3 + Open API 3 GUI")
            .title(" UserService Demo ").version("V1.0.0");

    return new OpenAPI().info(info).addServersItem(localServer);
  }
}