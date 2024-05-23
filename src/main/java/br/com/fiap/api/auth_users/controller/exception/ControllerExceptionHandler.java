package br.com.fiap.api.auth_users.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

  private StandardError err = new StandardError();

  @ExceptionHandler(ControllerNotFoundException.class)
  public ResponseEntity<StandardError> entityNotFound(
      ControllerNotFoundException e,
      HttpServletRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Entity not found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(this.err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validation(
      MethodArgumentNotValidException e,
      HttpServletRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;

    ValidateError error = new ValidateError();

    error.setTimestamp(Instant.now());
    error.setStatus(status.value());
    error.setError("Validation Error");
    error.setMessage(e.getMessage());
    error.setPath(request.getRequestURI());

    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error.addMessages(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return ResponseEntity.status(status).body(error);
  }

}
