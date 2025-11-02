package com.onlinelearningplatform.learningPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AssignmentAlreadyExistException extends  RuntimeException {
    public AssignmentAlreadyExistException(String message) {
        super(message);
    }

}
