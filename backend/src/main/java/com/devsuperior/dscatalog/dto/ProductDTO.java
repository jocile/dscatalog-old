package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object DTO - Data Transfer Object -
 * access the product entity,
 * used to load only the necessary data.
 * @param entity product
 * @see com.devsuperior.dscatalog.resources.CategoryResource CategoryResource
 */
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private String description;
  private Double price;
  private String imgUrl;
  private Instant date;

  private List<CategoryDTO> categories = new ArrayList<>();

  /**
   * this constructor creates a data transfer object
   *  from all properties except category list
   *
   * @param id
   * @param name
   * @param description
   * @param price
   * @param imgUrl
   * @param date
   */
  public ProdutcDTO(
    Long id,
    String name,
    String description,
    Double price,
    String imgUrl,
    Instant date
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
    this.date = date;
  }

  /**
   * this constructor creates a data transfer object
   *  from a product entity
   *
   * @param entity product
   */
  public ProdutcDTO(Product entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.price = entity.getPrice();
    this.imgUrl = entity.getImgUrl();
    this.date = entity.getDate();
  }

  /**
   * this constructor creates a data transfer object
   *  from a product entity and categories list
   *
   * @param entity product
   * @param categories list
   */
  public ProdutcDTO(Product entity, Set<Category> categories) {
    this(entity);
    categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
  }
}
