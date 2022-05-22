package com.ftn.MyHousebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAlreadyExists extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserAlreadyExists() {
        super();
    }

    public UserAlreadyExists(String message) {
        super(message);
    }

    public UserAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
