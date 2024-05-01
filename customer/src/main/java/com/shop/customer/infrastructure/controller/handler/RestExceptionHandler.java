package com.shop.customer.infrastructure.controller.handler;

import com.shop.customer.domain.exception.CustomerException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomerException.class)
  protected ResponseEntity<String> handleCustomerException(final CustomerException exception) {
    log.error(String.format("Customer exception. Error message: %s", exception.getMessage()));
    return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity<String> handleInternalException(final RuntimeException exception) {
    log.error(
        String.format(
            "Error occurred in the internal service. Error message: %s", exception.getMessage()));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
  }

  @ExceptionHandler(SQLException.class)
  protected ResponseEntity<String> handleSqlConflict(final SQLException exception) {
    log.error(
        String.format(
            "A Data Base related error was raised [%s] with error message: %s",
            exception.getClass(), exception.getMessage()));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {
    final Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              final String fieldName = ((FieldError) error).getField();
              final String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    log.error(
        String.format("Error risen while trying to bind value(s) to the attribute(s): %s", errors));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(CallNotPermittedException.class)
  protected ResponseEntity<String> handleCallNotPermittedException(
      final CallNotPermittedException exception) {
    log.error(
        String.format("Call Not Permitted Exception. Error message: %s", exception.getMessage()));
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exception.getMessage());
  }
}
