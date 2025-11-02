package com.onlinelearningplatform.learningPlatform.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponseDTO {
    private String apiPath;
    private String errorMessage;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;

    public ExceptionResponseDTO() {
    }

    public ExceptionResponseDTO(String apiPath, String errorMessage, HttpStatus errorCode, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorTime = errorTime;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(LocalDateTime errorTime) {
        this.errorTime = errorTime;
    }
}
