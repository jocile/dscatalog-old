package com.devsuperior.dscatalog.services.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundExceptions(String msg) {
    super(msg);
  }
}
