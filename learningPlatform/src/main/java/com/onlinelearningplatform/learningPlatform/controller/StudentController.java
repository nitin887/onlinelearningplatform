package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.StudentDTO;
import com.onlinelearningplatform.learningPlatform.serviceInterface.StudentServiceInterface;
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
@RequestMapping("/api/students")
public class StudentController {

    private final StudentServiceInterface studentService;

    public StudentController(StudentServiceInterface studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all students")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = "Get a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/id/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @Operation(summary = "Get a student by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{email:.+}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @Operation(summary = "Update a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/id/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, studentDTO));
    }

    @Operation(summary = "Delete a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/id/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get students by course ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found students for the course"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/by-course/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.getStudentsByCourseId(courseId));
    }
}
