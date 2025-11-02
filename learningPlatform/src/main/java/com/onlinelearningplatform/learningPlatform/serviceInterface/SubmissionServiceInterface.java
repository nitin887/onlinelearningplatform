package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.SubmissionDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface SubmissionServiceInterface {

    SubmissionDTO createSubmission(SubmissionDTO submissionDTO);

    SubmissionDTO updateSubmission(Long submissionId, SubmissionDTO submissionDTO);

    void deleteSubmission(Long submissionId);

    List<SubmissionDTO> getAllSubmissions();

    SubmissionDTO getSubmissionById(Long submissionId);

    List<SubmissionDTO> getSubmissionsByAssignmentId(Long assignmentId);

    // Assuming these methods should be implemented based on the updated interface
    @Transactional(readOnly = true)
    List<SubmissionDTO> getSubmissionsByDescription(String submissionDescription);

    List<SubmissionDTO> getSubmissionsByDueDate(LocalDateTime submissionDueDate);
}
