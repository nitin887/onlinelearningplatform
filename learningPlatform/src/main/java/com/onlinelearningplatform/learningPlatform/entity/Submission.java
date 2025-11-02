package com.onlinelearningplatform.learningPlatform.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    private LocalDateTime submittedAt;
    private String contentUrl; // Removed @GeneratedValue
    private Double grade;
    private String feedback;
    private String description;


    @ManyToOne
    @JoinColumn(name = "assignment_id") // Defines the foreign key to the Assignment table
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id") // Defines the foreign key to the Student table
    private Student student;

    public Submission() {
    }

    public Submission(Long submissionId, LocalDateTime submittedAt, String contentUrl, Double grade, String feedback, String description, Assignment assignment, Student student) {
        this.submissionId = submissionId;
        this.submittedAt = submittedAt;
        this.contentUrl = contentUrl;
        this.grade = grade;
        this.feedback = feedback;
        this.description = description;
        this.assignment = assignment;
        this.student = student;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
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

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
