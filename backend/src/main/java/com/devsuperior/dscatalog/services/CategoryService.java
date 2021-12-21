package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundExceptions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class of Service that creates a list of categories
 */

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository repository;

  /**
   * Converts the category list to a dto list;
   * use map stream (lambda) to transform elements;
   * Returns a list of dto categories.
   *
   * @return Category list
   */
  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    List<Category> list = repository.findAll();
    return list
      .stream()
      .map(x -> new CategoryDTO(x))
      .collect(Collectors.toList());
  }

  /**
   * Get the id and return a category
   *
   * @param id database identifier
   * @return Category
   */
  @Transactional(readOnly = true)
  public CategoryDTO findById(Long id) {
    Optional<Category> obj = repository.findById(id);
    Category entity = obj.orElseThrow(
      () -> new EntityNotFoundExceptions("Entity not found")
    );
    return new CategoryDTO(entity);
  }

  /**
   * Insert a new category in the repository
   *
   * @param category
   * @return entity Category DTO
   */
  @Transactional
  public CategoryDTO insert(CategoryDTO dto) {
    Category entity = new Category();
    entity.setName(dto.getName());
    entity = repository.save(entity);
    return new CategoryDTO(entity);
  }
}
