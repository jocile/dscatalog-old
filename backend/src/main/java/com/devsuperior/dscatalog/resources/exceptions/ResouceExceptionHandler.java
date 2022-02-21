package com.devsuperior.dscatalog.resources.exceptions;

import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class intercepts exceptions and performs custom handling
 */

@ControllerAdvice
public class ResouceExceptionHandler {

  /**
   * This method receives the entity not found error and returns the custom standard error
   *
   * @param e Entity Not Found Exception
   * @param request Http Servlet Request
   * @exception ResourceNotFoundExceptions
   * @return Standard Error resource not found exception
   */
  @ExceptionHandler(ResourceNotFoundExceptions.class)
  public ResponseEntity<StandardError> entityNotFound(
    ResourceNotFoundExceptions e,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Resource not found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  /**
   * This method receives the integrity database error and returns the custom standard error
   *
   * @param e Database Exception
   * @param request Http Servlet Request
   * @exception DatabaseException integrity exception
   * @return Standard Error for database integrity error
   */
  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> database(
    DatabaseException e,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Database exception");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }
}
