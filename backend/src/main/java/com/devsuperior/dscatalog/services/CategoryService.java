package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseExceptions;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
   *
   * @param pageRequest
   * @return Category paging results list
   */
  @Transactional(readOnly = true)
  public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
    Page<Category> list = repository.findAll(pageRequest);
    return list.map(x -> new CategoryDTO(x));
  }

  /**
   * Converts the category list to a dto list;
   * use map stream (lambda) to transform elements;
   *
   * @obsolete since it was replaced by findAllPaged
   * @return Category list of dto categories.
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
      () -> new ResourceNotFoundExceptions("Entity not found")
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

  /**
   * This method updates the category
   *
   * @param id category identifier
   * @param dto Category to update
   * @throws ResourseNotFoundException if the category identifier does not found
   */
  @Transactional
  public CategoryDTO update(Long id, CategoryDTO dto) {
    try {
      Category entity = repository.getOne(id);
      entity.setName(dto.getName());
      entity = repository.save(entity);
      return new CategoryDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundExceptions("Id not found " + id);
    }
  }

  /**
   * This method delete the category
   *
   * @param id
   * @throws ResourceNotFoundExceptions if the category identifier not found
   * @throws DatabaseExceptions if database integrity is violated
   */
  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundExceptions("Id not found " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseExceptions("Integrity violation");
    }
  }
}
