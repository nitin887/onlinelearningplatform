package com.onlinelearningplatform.learningPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstructorNotExistException extends RuntimeException {
    public InstructorNotExistException(String message) {
        super(message);
    }

}
