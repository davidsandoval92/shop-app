package com.shop.order.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EventException extends RuntimeException {

    private final HttpStatus httpStatus;

    public EventException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
