package com.onlinelearningplatform.learningPlatform.exception;

import com.onlinelearningplatform.learningPlatform.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleStudentAlreadyExistException(StudentAlreadyExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StudentNotExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleStudentNotExistException(StudentNotExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InstructorAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInstructorAlreadyExistException(InstructorAlreadyExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InstructorNotExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInstructorNotExistException(InstructorNotExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCourseAlreadyExistException(CourseAlreadyExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCourseNotFoundException(CourseNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LessonAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleLessonAlreadyExistException(LessonAlreadyExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LessonNotExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleLessonNotExistException(LessonNotExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AssignmentAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAssignmentAlreadyExistException(AssignmentAlreadyExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAssignmentNotFoundException(AssignmentNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubmissionAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleSubmissionAlreadyExistException(SubmissionAlreadyExistException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SubmissionNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleSubmissionNotFoundException(SubmissionNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        return buildErrorResponse(ex, "Incorrect username or password", request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return buildErrorResponse(ex, "You do not have permission to access this resource.", request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataProcessingException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDataProcessingException(DataProcessingException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex, WebRequest request) {
        return buildErrorResponse(ex, "An unexpected error occurred.", request, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private ResponseEntity<ExceptionResponseDTO> buildErrorResponse(Exception ex, WebRequest request, HttpStatus status) {
        return buildErrorResponse(ex, ex.getMessage(), request, status);
    }

    private ResponseEntity<ExceptionResponseDTO> buildErrorResponse(Exception ex, String message, WebRequest request, HttpStatus status) {
        ExceptionResponseDTO errorDetails = new ExceptionResponseDTO(
                request.getDescription(false).replace("uri=", ""),
                message,
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetails, status);
    }
}
