package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.InstructorDTO;
import com.onlinelearningplatform.learningPlatform.serviceInterface.InstructorServiceInterface;
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
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorServiceInterface instructorService;

    public InstructorController (InstructorServiceInterface instructorService) {
        this.instructorService = instructorService;
    }

    @Operation(summary = "Create a new instructor (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instructor created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody InstructorDTO instructorDTO){
        InstructorDTO createdInstructor = instructorService.createInstructor(instructorDTO);
        return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all instructors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all instructors")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<List<InstructorDTO>> getAllInstructors(){
        List<InstructorDTO> allInstructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(allInstructors);
    }

    @Operation(summary = "Get an instructor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the instructor"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{instructorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable Long instructorId){
        InstructorDTO instructor = instructorService.getInstructorById(instructorId);
        return ResponseEntity.ok(instructor);
    }

    @Operation(summary = "Get an instructor by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the instructor"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/by-email")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<InstructorDTO> getInstructorByEmail(@RequestParam String email){
        InstructorDTO instructor = instructorService.getInstructorByEmail(email);
        return ResponseEntity.ok(instructor);
    }

    @Operation(summary = "Update an instructor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor updated successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{instructorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorDTO instructorDTO){
        InstructorDTO updatedInstructor = instructorService.updateInstructor(instructorId, instructorDTO);
        return ResponseEntity.ok(updatedInstructor);
    }

    @Operation(summary = "Delete an instructor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Instructor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{instructorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long instructorId){
        instructorService.deleteInstructor(instructorId);
        return ResponseEntity.noContent().build();
    }
}
