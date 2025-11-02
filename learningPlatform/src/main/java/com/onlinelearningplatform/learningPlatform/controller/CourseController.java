package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.CourseDTO;
import com.onlinelearningplatform.learningPlatform.dto.LessonDTO;
import com.onlinelearningplatform.learningPlatform.service.CourseService;
import com.onlinelearningplatform.learningPlatform.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course Management", description = "APIs for managing courses and their lessons")
public class CourseController {

    private final CourseService courseService;
    private final LessonService lessonService;

    public CourseController(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @Operation(summary = "Create a new course", description = "Creates a new course in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid course details supplied")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseDTO> createCourse(
            @Parameter(description = "Course details to create", required = true)
            @RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all courses", description = "Retrieves a list of all courses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of courses retrieved successfully")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @Operation(summary = "Get course by ID", description = "Retrieves a course by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(
            @Parameter(description = "ID of the course to retrieve", required = true)
            @PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @Operation(summary = "Update an existing course", description = "Updates a course's details by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid course details supplied"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseDTO> updateCourse(
            @Parameter(description = "ID of the course to update", required = true)
            @PathVariable Long courseId,
            @Parameter(description = "Course details to update", required = true)
            @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDTO));
    }

    @Operation(summary = "Delete a course", description = "Deletes a course by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Void> deleteCourse(
            @Parameter(description = "ID of the course to delete", required = true)
            @PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create a new lesson for a course", description = "Creates a new lesson associated with a specific course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lesson created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid lesson details supplied"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{courseId}/lessons")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<LessonDTO> createLesson(
            @Parameter(description = "ID of the course to add the lesson to", required = true)
            @PathVariable Long courseId,
            @Parameter(description = "Lesson details to create", required = true)
            @RequestBody LessonDTO lessonDTO) {
        LessonDTO createdLesson = lessonService.createLesson(courseId, lessonDTO);
        return new ResponseEntity<>(createdLesson, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all lessons for a course", description = "Retrieves a list of all lessons for a specific course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of lessons retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{courseId}/lessons")
    public ResponseEntity<List<LessonDTO>> getLessonsByCourse(
            @Parameter(description = "ID of the course to retrieve lessons from", required = true)
            @PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.getLessonsByCourseId(courseId));
    }
}
