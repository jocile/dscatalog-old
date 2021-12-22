package com.devsuperior.dscatalog.resources.exceptions;

import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class intercepts exceptions and performs custom handling
 *
 *  @author Nelio Alves acenelio
 */

@ControllerAdvice
public class ResouceExceptionHandler {

  /**
   * This method receives the entity not found error and returns the custom standard error
   *
   * @param e Entity Not Found Exception
   * @param request Http Servlet Request
   * @return Standard Error
   */
  @ExceptionHandler(ResourceNotFoundExceptions.class)
  public ResponseEntity<StandardError> entityNotFound(
    ResourceNotFoundExceptions e,
    HttpServletRequest request
  ) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    err.setError("Resource not found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }
}
