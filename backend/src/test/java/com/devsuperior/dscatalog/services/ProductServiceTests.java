package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
  @InjectMocks
  private ProductService service;

  @Mock
  private ProductRepository repository;

  private long existingId;
  private long nonExistingId;
  private long dependentId;

  @BeforeEach
  void setUp() throws Exception {
    existingId = 1L;
    nonExistingId = 1000L;
    dependentId = 4L;

    Mockito.doNothing().when(repository).deleteById(existingId);
    Mockito
      .doThrow(EmptyResultDataAccessException.class)
      .when(repository)
      .deleteById(nonExistingId);
    Mockito
      .doThrow(DataIntegrityViolationException.class)
      .when(repository)
      .deleteById(dependentId);
  }

  @Test
  public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
    Assertions.assertThrows(
      DatabaseException.class,
      () -> {
        service.delete(dependentId);
      }
    );

    Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
  }

  @Test
  public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
    Assertions.assertThrows(
      ResourceNotFoundExceptions.class,
      () -> {
        service.delete(nonExistingId);
      }
    );

    Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
  }

  @Test
  public void deleteShouldDoNothingWhenIdExists() {
    Assertions.assertDoesNotThrow(
      () -> {
        service.delete(existingId);
      }
    );

    Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
  }
}
