package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
  @Autowired
  private CategoryService service;

  @GetMapping
  @Operation(
    summary = "Lista as categorias",
    description = "Lista todas as categorias",
    tags = { "Categorias" },
    responses = {
      @ApiResponse(
        description = "Sucesso",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = CategoryDTO.class)
        )
      ),
    }
  )
  public ResponseEntity<List<CategoryDTO>> findAll() {
    List<CategoryDTO> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
    CategoryDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }
}
