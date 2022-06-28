package com.ftn.MyHousebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LogInFailedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public LogInFailedException() {
        super();
    }

    public LogInFailedException(String message) {
        super(message);
    }

    public LogInFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
