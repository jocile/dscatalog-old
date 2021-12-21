package com.devsuperior.dscatalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class using documentation with OpenApi 3 implemented with Swagger
 *
 * To interact with the API run Spring Boot and go to:
 * <a href="http://localhost:8080/swagger-ui/index.html">swagger-ui</a>
 */

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "DevSuperior Catálogo",
    version = "0.1.0",
    description = "Catalog app with CRUD and product listing"
  )
)
public class DscatalogApplication {

  public static void main(String[] args) {
    SpringApplication.run(DscatalogApplication.class, args);
  }
}
