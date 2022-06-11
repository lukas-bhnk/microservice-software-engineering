package com.nutrition.sweng.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class ProcessException extends RuntimeException{
    public ProcessException(String message) {
        super(message);
    }
}
