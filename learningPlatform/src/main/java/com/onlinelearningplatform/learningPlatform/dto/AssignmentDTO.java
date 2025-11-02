package com.onlinelearningplatform.learningPlatform.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AssignmentDTO {
    private Long assignmentId;
    private Long courseId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private List<SubmissionDTO> submissions;

    public AssignmentDTO() {
    }

    public AssignmentDTO(Long assignmentId, Long courseId, String title, String description, LocalDateTime dueDate, List<SubmissionDTO> submissions) {
        this.assignmentId = assignmentId;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.submissions = submissions;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }
}
