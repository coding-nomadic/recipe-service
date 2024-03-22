package com.token.service.exceptions;


import com.token.service.models.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllException(Exception exception, WebRequest webRequest) {
        exception.printStackTrace();
        log.info(exception.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<ErrorDetails> handleTokenException(TokenServiceException exception, WebRequest webRequest) {
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> usernameNotFoundException(UsernameNotFoundException exception, WebRequest webRequest) {
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    // handle specific exceptions
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorDetails> handleMethodNotFoundException(ResourceNotFoundException exception,
                                                                      WebRequest webRequest) {
        exception.printStackTrace();
        log.info(exception.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getLocalizedMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getLocalizedMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}