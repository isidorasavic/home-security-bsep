package com.ftn.MyHousebackend.configuration;

import com.ftn.MyHousebackend.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RoleNotFound.class)
    protected ResponseEntity<Object> handleRoleNotFoundException(RoleNotFound e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND);
        error.setMessage(e.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExists e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE);
        error.setMessage(e.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND);
        error.setMessage(e.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(ObjectNotFound.class)
    protected ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFound e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND);
        error.setMessage(e.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    protected ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE);
        error.setMessage(e.getMessage());
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

}
