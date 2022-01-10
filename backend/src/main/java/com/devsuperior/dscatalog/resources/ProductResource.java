package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller class
 *  have been added documentation with OpenApi 3 implemented with Swagger
 *
 * To interact with the API run Spring Boot and go to:
 * <a href="http://localhost:8080/swagger-ui/index.html">swagger-ui</a>
 */

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
  @Autowired
  private ProductService service;

  /**
   * Paged list of all products
   *
   * @param page optional page number
   * @param linesPerPage optional number of items per page
   * @param orderBy optional order of page items
   * @param direction optional ascending or descending order of page items
   * @return Product list
   */
  @GetMapping
  @Operation(
    summary = "Product list",
    description = "Paged list of all products",
    tags = { "products" }
  )
  public ResponseEntity<Page<ProductDTO>> findAll(
    @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(
      value = "linesPerPage",
      defaultValue = "12"
    ) Integer linesPerPage,
    @RequestParam(value = "direction", defaultValue = "ASC") String direction,
    @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
  ) {
    PageRequest pageRequest = PageRequest.of(
      page,
      linesPerPage,
      Direction.valueOf(direction),
      orderBy
    );

    Page<ProductDTO> list = service.findAllPaged(pageRequest);

    return ResponseEntity.ok().body(list);
  }

  /**
   * Search product by identified number
   *
   * @param id identifier URL Parameter
   * @return Product
   */
  @GetMapping(value = "/{id}")
  @Operation(
    operationId = "findById",
    summary = "search product by identified number",
    description = "Receives the identifier and returns the corresponding product",
    tags = { "products" },
    responses = {
      @ApiResponse(
        description = "Success! The product was found!",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Product.class)
        )
      ),
      @ApiResponse(
        description = "Error! Identifier number not found!",
        responseCode = "404",
        content = @Content
      ),
    }
  )
  public ResponseEntity<ProductDTO> findById(
    @Parameter(
      description = "Product identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    ProductDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  /**
   * Insert a new product and return confirmation response
   *
   * @param  product Request Body with product name
   * @return confirmation status response with created and location header
   */
  @PostMapping
  @Operation(
    summary = "Insert a new product",
    description = "Insert a new product and return confirmation response",
    tags = { "products" },
    responses = {
      @ApiResponse(
        description = "Success insert new product name",
        responseCode = "201",
        content = @Content(mediaType = "application/json")
      ),
      @ApiResponse(
        description = "Product name error",
        responseCode = "400",
        content = @Content
      ),
    }
  )
  public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
    dto = service.insert(dto);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(dto.getId())
      .toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  /**
   * Update the product and return confirmation response
   *
   * @param  product Request Body with product name
   * @return confirmation status response with created and location header
   */
  @PutMapping(value = "/{id}")
  @Operation(
    summary = "Update the product",
    description = "Update the product and return the updated",
    tags = { "products" },
    responses = {
      @ApiResponse(
        description = "Success updade product",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Product.class)
        )
      ),
      @ApiResponse(
        description = "Product identifier not found",
        responseCode = "400",
        content = @Content
      ),
    }
  )
  public ResponseEntity<ProductDTO> update(
    @Parameter(
      name = "id",
      description = "Product identifier number",
      required = true
    ) @PathVariable Long id,
    @RequestBody ProductDTO dto
  ) {
    dto = service.update(id, dto);
    return ResponseEntity.ok().body(dto);
  }

  /**
   * Update the product and return confirmation response
   *
   * @param  product Request Body with product name
   * @return confirmation status response with created and location header
   */
  @DeleteMapping(value = "/{id}")
  @Operation(
    summary = "Delete the product",
    description = "Delete the product and return the updated",
    tags = { "products" },
    responses = {
      @ApiResponse(
        description = "The product deleted successfully",
        responseCode = "204",
        content = @Content
      ),
      @ApiResponse(
        description = "Product identifier not found",
        responseCode = "404",
        content = @Content
      ),
    }
  )
  public ResponseEntity<Void> delete(
    @Parameter(
      name = "id",
      description = "Product identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
