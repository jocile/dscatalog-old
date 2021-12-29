package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object DTO - Data Transfer Object -
 * access the category entity,
 * used to load only the necessary data.
 *
 * @param entity category
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

  /**
   * this constructor creates a data transfer object
   *  from a category entity
   *
   * @param entity category
   */
  public CategoryDTO(Category entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }
}
