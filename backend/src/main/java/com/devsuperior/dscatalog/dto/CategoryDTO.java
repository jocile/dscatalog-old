package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto DTO - Data Transfer Object -
 * access the entity,
 * used to load only the necessary data.
 *
 * @param entity
 * @see com.devsuperior.dscatalog.resources.CategoryResource CategoryResource
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;

  public CategoryDTO(Category entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }
}
