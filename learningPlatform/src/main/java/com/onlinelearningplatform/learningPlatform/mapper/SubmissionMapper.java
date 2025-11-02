package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.SubmissionDTO;
import com.onlinelearningplatform.learningPlatform.entity.Submission;

public class SubmissionMapper {

    private SubmissionMapper(){

    }
public static Submission toSubmissionEntity(SubmissionDTO submissionDTO){
    if(submissionDTO==null){
        return null;
    }
    Submission submission = new Submission();
    submission.setSubmissionId(submissionDTO.getSubmissionId());
    submission.setSubmittedAt(submissionDTO.getSubmittedAt());
    submission.setContentUrl(submissionDTO.getContentUrl());
    submission.setGrade(submissionDTO.getGrade());
    submission.setFeedback(submissionDTO.getFeedback());
    submission.setDescription(submissionDTO.getDescription());
    // assignment and student will be set in service
    return submission;
}
public static SubmissionDTO toSubmissionDTO(Submission submission){
    if(submission==null){
        return null;
    }
    SubmissionDTO submissionDTO = new SubmissionDTO();
    submissionDTO.setSubmissionId(submission.getSubmissionId());
    if(submission.getAssignment() != null)
        submissionDTO.setAssignmentId(submission.getAssignment().getAssignmentId());
    if(submission.getStudent() != null)
        submissionDTO.setStudentId(submission.getStudent().getStudentId());
    submissionDTO.setSubmittedAt(submission.getSubmittedAt());
    submissionDTO.setContentUrl(submission.getContentUrl());
    submissionDTO.setGrade(submission.getGrade());
    submissionDTO.setFeedback(submission.getFeedback());
    submissionDTO.setDescription(submission.getDescription());
    return submissionDTO;
}
}
