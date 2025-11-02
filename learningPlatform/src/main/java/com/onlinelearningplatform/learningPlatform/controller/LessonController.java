package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.LessonDTO;
import com.onlinelearningplatform.learningPlatform.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @Operation(summary = "Get a lesson by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the lesson"),
            @ApiResponse(responseCode = "404", description = "Lesson not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonService.getLessonById(lessonId));
    }

    @Operation(summary = "Delete a lesson by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lesson deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Lesson not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }
}
