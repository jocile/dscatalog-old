package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class of Service that creates a list of categories
 */

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Autowired
  private CategoryRepository categoryRepository;

  /**
   * Converts the product list to a dto list;
   * use map stream (lambda) to transform elements;
   *
   * @param Pageable
   * @return Product paging results list
   */
  @Transactional(readOnly = true)
  public Page<ProductDTO> findAllPaged(Pageable pageable) {
    Page<Product> list = repository.findAll(pageable);
    return list.map(x -> new ProductDTO(x));
  }

  /**
   * Converts the product list to a dto list;
   * use map stream (lambda) to transform elements;
   *
   * @obsolete since it was replaced by findAllPaged
   * @return Product list of dto categories.
   */
  @Transactional(readOnly = true)
  public List<ProductDTO> findAll() {
    List<Product> list = repository.findAll();
    return list
      .stream()
      .map(x -> new ProductDTO(x))
      .collect(Collectors.toList());
  }

  /**
   * Get the id and return a product
   *
   * @param id database identifier
   * @return Product
   */
  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Optional<Product> obj = repository.findById(id);
    Product entity = obj.orElseThrow(
      () -> new ResourceNotFoundExceptions("Entity not found")
    );
    return new ProductDTO(entity, entity.getCategories());
  }

  /**
   * Insert a new product in the repository
   *
   * @param product
   * @return entity Product DTO
   */
  @Transactional
  public ProductDTO insert(ProductDTO dto) {
    Product entity = new Product();
    copyDtoToEntity(dto, entity);
    entity = repository.save(entity);
    return new ProductDTO(entity);
  }

  /**
   * This method updates the product
   *
   * @param id product identifier
   * @param dto Product to update
   * @throws ResourseNotFoundException if the product identifier does not found
   */
  @Transactional
  public ProductDTO update(Long id, ProductDTO dto) {
    try {
      Product entity = repository.getOne(id);
      copyDtoToEntity(dto, entity);
      entity = repository.save(entity);
      return new ProductDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundExceptions("Id not found " + id);
    }
  }

  /**
   * This method delete the product
   *
   * @param id
   * @throws ResourceNotFoundExceptions if the product identifier not found
   * @throws DatabaseException if database integrity is violated
   */
  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundExceptions("Id not found " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation");
    }
  }

  /**
   * This method receives the product object data to populate the product entity
   *
   * @param dto Product data object
   * @param entity product
   */
  private void copyDtoToEntity(ProductDTO dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setDate(dto.getDate());
    entity.setImgUrl(dto.getImgUrl());
    entity.setPrice(dto.getPrice());
    entity.getCategories().clear();
    for (CategoryDTO catDto : dto.getCategories()) {
      Category category = categoryRepository.getOne(catDto.getId());
      entity.getCategories().add(category);
    }
  }
}
