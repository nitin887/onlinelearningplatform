package com.onlinelearningplatform.learningPlatform.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CourseDTO {
    private Long courseId;
    private String title;
    private String description;
    private String category;
    private LocalDateTime createdAt;
    private Long instructorId; // Use ID to avoid circular dependency
    private List<LessonDTO> lessons;
    private List<AssignmentDTO> assignments;
    // private List<StudentDTO> students; // Student list is often large and excluded from main DTOs

    public CourseDTO() {
    }

    public CourseDTO(Long courseId, String title, String description, String category, LocalDateTime createdAt, Long instructorId, List<LessonDTO> lessons, List<AssignmentDTO> assignments) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
        this.instructorId = instructorId;
        this.lessons = lessons;
        this.assignments = assignments;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public List<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDTO> lessons) {
        this.lessons = lessons;
    }

    public List<AssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentDTO> assignments) {
        this.assignments = assignments;
    }
}
