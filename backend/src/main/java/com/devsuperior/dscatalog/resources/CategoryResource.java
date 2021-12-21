package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe controller using documentation with OpenApi 3 implemented with Swagger
 *
 * To interact with the API run Spring Boot and go to:
 * <a href="http://localhost:8080/swagger-ui/index.html">swagger-ui</a>
 */

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
  @Autowired
  private CategoryService service;

  /**
   * List all categories
   *
   * @return Category list
   */
  @GetMapping
  @Operation(
    summary = "Category list",
    description = "List all categories",
    tags = { "categories" }
  )
  public ResponseEntity<List<CategoryDTO>> findAll() {
    List<CategoryDTO> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  /**
   * Category search by id
   *
   * @param id identifier
   * @return Category
   */
  @GetMapping(value = "/{id}")
  @Operation(
    summary = "Category search by identifier",
    description = "Receives the identifier and returns the corresponding category",
    tags = { "categories" }
  )
  public ResponseEntity<CategoryDTO> findById(
    @Parameter(
      description = "identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    CategoryDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }
}
