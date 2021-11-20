package com.devsuperior.dscatalog.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;
}
