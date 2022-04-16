package com.devsuperior.dscatalog.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import com.devsuperior.dscatalog.tests.Factory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Load context from web layer only to do the tests
 *  using @WebMvcTest, and simulating components with @MockBean.
 */
@WebMvcTest(ProductResource.class)
public class ProductResourceTests {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService service;

  private Long existingId;
  private Long nonExistingId;
  private ProductDTO productDTO;
  private PageImpl<ProductDTO> page;

  @BeforeEach
  void setup() throws Exception {
    existingId = 1L;
    nonExistingId = 2L;

    productDTO = Factory.createProductDTO();
    page = new PageImpl<>(List.of(productDTO));

    when(service.findAllPaged(any())).thenReturn(page);

    when(service.findById(existingId)).thenReturn(productDTO);
    when(service.findById(nonExistingId))
      .thenThrow(ResourceNotFoundExceptions.class);
  }

  @Test
  public void findAllShouldReturnPage() throws Exception {
    ResultActions result = mockMvc.perform(
      get("/products").accept(MediaType.APPLICATION_JSON)
    );

    result.andExpect(status().isOk());
  }

  @Test
  public void findByIdShouldReturnProductWhenIdIsNotSet() throws Exception {
    ResultActions result = mockMvc.perform(
      get("/products/{id}", existingId).accept(MediaType.APPLICATION_JSON)
    );

    result.andExpect(status().isOk());
    result.andExpect(jsonPath("$.id").exists());
    result.andExpect(jsonPath("$.name").exists());
    result.andExpect(jsonPath("$.description").exists());
  }

  @Test
  public void findByIdShouldReturnNotFoundWhenIdDoesNotExist()
    throws Exception {
    ResultActions result = mockMvc.perform(
      get("/products/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON)
    );

    result.andExpect(status().isNotFound());
  }
}
