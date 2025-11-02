package com.onlinelearningplatform.learningPlatform.dto;

import java.time.LocalDateTime;

public class SubmissionDTO {
    private Long submissionId;
    private Long assignmentId;
    private Long studentId;
    private LocalDateTime submittedAt;
    private  String contentUrl;
    private  Double grade;
    private  String feedback;
    private  String description;

    public SubmissionDTO() {
    }

    public SubmissionDTO(Long submissionId, Long assignmentId, Long studentId, LocalDateTime submittedAt, String contentUrl, Double grade, String feedback, String description) {
        this.submissionId = submissionId;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.submittedAt = submittedAt;
        this.contentUrl = contentUrl;
        this.grade = grade;
        this.feedback = feedback;
        this.description = description;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
