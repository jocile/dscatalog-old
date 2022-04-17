package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceIntegrationTest {
  @Autowired
  private ProductService service;

  @Autowired
  private ProductRepository repository;

  private Long existingId;
  private Long nonExistingId;
  private Long countTotalProducts;

  @BeforeEach
  void setup() throws Exception {
    existingId = 1L;
    nonExistingId = 1000L;
    countTotalProducts = 25L;
  }

  @Test
  public void deleteShouldDeleteResourceWhenIdExists() {
    service.delete(existingId);

    Assertions.assertEquals(countTotalProducts - 1, repository.count());
  }

  @Test
  public void deleteShouldThrowExceptionWhenIdDoesNotExists() {
    Assertions.assertThrows(
      ResourceNotFoundExceptions.class,
      () -> {
        service.delete(nonExistingId);
      }
    );
  }
}
