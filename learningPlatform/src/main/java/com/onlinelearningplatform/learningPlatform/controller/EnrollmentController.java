package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.EnrollmentDTO;
import com.onlinelearningplatform.learningPlatform.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@Tag(name = "Enrollment Management", description = "APIs for managing course enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(summary = "Create a new enrollment", description = "Creates a new course enrollment.")
    @ApiResponse(responseCode = "201", description = "Enrollment created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid enrollment details supplied")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnrollmentDTO> createEnrollment(
            @Parameter(description = "Enrollment details to create", required = true)
            @RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all enrollments", description = "Retrieves a list of all course enrollments.")
    @ApiResponse(responseCode = "200", description = "List of enrollments retrieved successfully")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @Operation(summary = "Get enrollment by ID", description = "Retrieves a course enrollment by its unique ID.")
    @ApiResponse(responseCode = "200", description = "Enrollment found successfully")
    @ApiResponse(responseCode = "404", description = "Enrollment not found")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{enrollmentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(
            @Parameter(description = "ID of the enrollment to retrieve", required = true)
            @PathVariable Long enrollmentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(enrollmentId));
    }

    @Operation(summary = "Delete an enrollment", description = "Deletes a course enrollment by ID.")
    @ApiResponse(responseCode = "204", description = "Enrollment deleted successfully")
    @ApiResponse(responseCode = "404", description = "Enrollment not found")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{enrollmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEnrollment(
            @Parameter(description = "ID of the enrollment to delete", required = true)
            @PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}
