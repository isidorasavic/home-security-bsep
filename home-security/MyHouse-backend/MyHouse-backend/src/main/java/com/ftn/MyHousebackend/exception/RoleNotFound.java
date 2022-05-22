package com.ftn.MyHousebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RoleNotFound() {
        super();
    }

    public RoleNotFound(String message) {
        super(message);
    }

    public RoleNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
