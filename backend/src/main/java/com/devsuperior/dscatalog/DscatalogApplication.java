package com.devsuperior.dscatalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "DevSuperior Catálogo",
    version = "0.1.0",
    description = "App de catálogo com CRUD e listagem de produtos"
  )
)
public class DscatalogApplication {

  public static void main(String[] args) {
    SpringApplication.run(DscatalogApplication.class, args);
  }
}
