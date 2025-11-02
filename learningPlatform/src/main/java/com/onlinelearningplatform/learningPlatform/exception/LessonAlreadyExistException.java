package com.onlinelearningplatform.learningPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LessonAlreadyExistException extends RuntimeException {
    public LessonAlreadyExistException(String message) {
        super(message);
    }

}
