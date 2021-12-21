package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

  /**
   * Insert a new category and return confirmation response
   *
   * @param  category Request Body with category name
   * @return confirmation status response with created and location header
   */
  @PostMapping
  @Operation(
    summary = "Insert a new category",
    description = "Insert a new category and return confirmation response",
    tags = { "categories" },
    responses = {
      @ApiResponse(
        description = "Success insert category",
        responseCode = "201",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Category.class)
        )
      ),
      @ApiResponse(
        description = "Category name error",
        responseCode = "400",
        content = @Content
      ),
    }
  )
  public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
    dto = service.insert(dto);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(dto.getId())
      .toUri();
    return ResponseEntity.created(uri).body(dto);
  }
}
