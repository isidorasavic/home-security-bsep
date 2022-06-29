package com.ftn.adminbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserContainsObjectsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserContainsObjectsException() {
        super();
    }

    public UserContainsObjectsException(String message) {
        super(message);
    }

    public UserContainsObjectsException(String message, Throwable cause) {
        super(message, cause);
    }
}
