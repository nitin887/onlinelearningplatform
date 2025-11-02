package com.onlinelearningplatform.learningPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InstructorAlreadyExistException extends RuntimeException {
    public InstructorAlreadyExistException(String message) {
        super(message);
    }

}
