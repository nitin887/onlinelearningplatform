package com.onlinelearningplatform.learningPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotExistException extends RuntimeException {
    public StudentNotExistException(String message) {
        super(message);
    }

}
