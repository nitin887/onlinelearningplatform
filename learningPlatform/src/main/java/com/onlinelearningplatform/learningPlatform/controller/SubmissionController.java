package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.SubmissionDTO;
import com.onlinelearningplatform.learningPlatform.serviceInterface.SubmissionServiceInterface;
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
@RequestMapping("/api/submissions")
@Tag(name = "Submission Management", description = "APIs for managing assignment submissions")
public class SubmissionController {
    private final SubmissionServiceInterface submissionService;

    public SubmissionController(SubmissionServiceInterface submissionService) {
        this.submissionService = submissionService;
    }

    @Operation(summary = "Create a new submission", description = "Creates a new assignment submission.")
    @ApiResponse(responseCode = "201", description = "Submission created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid submission details supplied")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<SubmissionDTO> createSubmission(
            @Parameter(description = "Submission details to create", required = true)
            @RequestBody SubmissionDTO submissionDTO) {
        SubmissionDTO createdSubmission = submissionService.createSubmission(submissionDTO);
        return new ResponseEntity<>(createdSubmission, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all submissions", description = "Retrieves a list of all assignment submissions.")
    @ApiResponse(responseCode = "200", description = "List of submissions retrieved successfully")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<List<SubmissionDTO>> getAllSubmissions() {
        List<SubmissionDTO> allSubmissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(allSubmissions);
    }

    @Operation(summary = "Get submission by ID", description = "Retrieves an assignment submission by its unique ID.")
    @ApiResponse(responseCode = "200", description = "Submission found successfully")
    @ApiResponse(responseCode = "404", description = "Submission not found")
    @SecurityRequirement(name = "bearerAuth")

    @GetMapping("/{submissionId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<SubmissionDTO> getSubmissionById(
            @Parameter(description = "ID of the submission to retrieve", required = true)
            @PathVariable Long submissionId) {
        SubmissionDTO submissionById = submissionService.getSubmissionById(submissionId);
        return ResponseEntity.ok(submissionById);
    }

    @Operation(summary = "Delete a submission", description = "Deletes an assignment submission by ID.")
    @ApiResponse(responseCode = "204", description = "Submission deleted successfully")
    @ApiResponse(responseCode = "404", description = "Submission not found")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{submissionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSubmission(
            @Parameter(description = "ID of the submission to delete", required = true)
            @PathVariable Long submissionId) {
        submissionService.deleteSubmission(submissionId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update an existing submission", description = "Updates an assignment submission's details by ID.")
    @ApiResponse(responseCode = "200", description = "Submission updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid submission details supplied")
    @ApiResponse(responseCode = "404", description = "Submission not found")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{submissionId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<SubmissionDTO> updateSubmission(
            @Parameter(description = "ID of the submission to update", required = true)
            @PathVariable Long submissionId,
            @Parameter(description = "Submission details to update", required = true)
            @RequestBody SubmissionDTO submissionDTO) {
        SubmissionDTO updatedSubmission = submissionService.updateSubmission(submissionId, submissionDTO);
        return ResponseEntity.ok(updatedSubmission);
    }
}
