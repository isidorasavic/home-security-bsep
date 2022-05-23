package com.ftn.adminbackend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }

    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
