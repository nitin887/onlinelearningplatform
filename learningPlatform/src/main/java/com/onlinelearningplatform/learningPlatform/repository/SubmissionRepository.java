package com.onlinelearningplatform.learningPlatform.repository;

import com.onlinelearningplatform.learningPlatform.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {
    List<Submission> findByStudent_StudentId(Long studentId);
    List<Submission> findByAssignment_AssignmentId(Long assignmentId);
    Optional<Submission> findByContentUrl(String contentUrl);
    Optional<Submission> findByGrade(Double grade);
    Optional<Submission> findByFeedback(String feedback);
    List<Submission> findBySubmittedAt(LocalDateTime submissionDueDate);
    List<Submission> findByDescription(String description);
}
