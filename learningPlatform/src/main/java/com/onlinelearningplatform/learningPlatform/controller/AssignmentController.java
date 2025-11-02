package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.AssignmentDTO;
import com.onlinelearningplatform.learningPlatform.dto.SubmissionDTO;
import com.onlinelearningplatform.learningPlatform.serviceInterface.AssignmentServiceInterface;
import com.onlinelearningplatform.learningPlatform.serviceInterface.SubmissionServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentServiceInterface assignmentService;
    private final SubmissionServiceInterface submissionService;

    public AssignmentController(AssignmentServiceInterface assignmentService, SubmissionServiceInterface submissionService){
        this.assignmentService = assignmentService;
        this.submissionService = submissionService;
    }

    @Operation(summary = "Create a new assignment (Admin/Instructor only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Assignment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<AssignmentDTO> createAssignment(@RequestBody AssignmentDTO assignmentDTO){
        AssignmentDTO createdAssignment=assignmentService.createAssignment(assignmentDTO);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an assignment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment updated successfully"),
            @ApiResponse(responseCode = "404", description = "Assignment not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{assignmentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<AssignmentDTO>  updateAssignment(@PathVariable Long assignmentId, @RequestBody AssignmentDTO assignmentDTO){
        AssignmentDTO updatedAssignment=assignmentService.updateAssignment(assignmentId, assignmentDTO);
        return ResponseEntity.ok(updatedAssignment);
    }

    @Operation(summary = "Delete an assignment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Assignment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Assignment not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{assignmentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long assignmentId){
        assignmentService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all assignments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all assignments")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments(){
        List<AssignmentDTO> allAssignment= assignmentService.getAllAssignments();
        return ResponseEntity.ok(allAssignment);
    }

    @Operation(summary = "Get an assignment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the assignment"),
            @ApiResponse(responseCode = "404", description = "Assignment not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{assignmentId}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(@PathVariable Long assignmentId){
        AssignmentDTO assignmentById=assignmentService.getAssignmentById(assignmentId);
        return ResponseEntity.ok(assignmentById);
    }

    @Operation(summary = "Get assignments by course ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found assignments for the course"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByCourseId(courseId));
    }

    @Operation(summary = "Get all submissions for an assignment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found submissions for the assignment"),
            @ApiResponse(responseCode = "404", description = "Assignment not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{assignmentId}/submissions")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignmentId(assignmentId));
    }
}
