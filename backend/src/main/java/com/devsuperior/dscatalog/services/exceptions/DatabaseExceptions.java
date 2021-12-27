package com.devsuperior.dscatalog.services.exceptions;

public class DatabaseExceptions extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DatabaseExceptions(String msg) {
    super(msg);
  }
}
