package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTests {
  @Autowired
  private ProductRepository repository;

  @Test
  public void deleteShouldDeleteObjectWhenIdExists() {
    long existingId = 1L;

    repository.deleteById(existingId);

    repository.findById(existingId);

    Optional<Product> result = repository.findById(existingId);
    Assertions.assertFalse(result.isPresent());
  }
}
