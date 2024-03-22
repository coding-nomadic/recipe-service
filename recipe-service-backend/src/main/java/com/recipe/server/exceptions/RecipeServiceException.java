package com.recipe.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class RecipeServiceException extends RuntimeException {


    public RecipeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipeServiceException(String message, String errorCode) {
        super(message);
    }
}
