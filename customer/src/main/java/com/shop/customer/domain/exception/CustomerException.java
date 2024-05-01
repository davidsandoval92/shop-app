package com.shop.customer.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerException extends RuntimeException {

  private final HttpStatus httpStatus;
  public CustomerException(final String message, final HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
