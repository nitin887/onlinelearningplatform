package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.SubmissionDTO;
import com.onlinelearningplatform.learningPlatform.entity.Assignment;
import com.onlinelearningplatform.learningPlatform.entity.Student;
import com.onlinelearningplatform.learningPlatform.entity.Submission;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.SubmissionMapper;
import com.onlinelearningplatform.learningPlatform.repository.AssignmentRepository;
import com.onlinelearningplatform.learningPlatform.repository.StudentRepository;
import com.onlinelearningplatform.learningPlatform.repository.SubmissionRepository;
import com.onlinelearningplatform.learningPlatform.serviceInterface.SubmissionServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class SubmissionService implements SubmissionServiceInterface {

    
    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;

    // Manually added constructor for dependency injection
    public SubmissionService(SubmissionRepository submissionRepository, AssignmentRepository assignmentRepository, StudentRepository studentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }

 // Injected as a bean

    @Override
    @Transactional
    public SubmissionDTO createSubmission(SubmissionDTO submissionDTO){
    Assignment assignment = assignmentRepository.findById(submissionDTO.getAssignmentId())
        .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + submissionDTO.getAssignmentId()));
    Student student = studentRepository.findById(submissionDTO.getStudentId())
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + submissionDTO.getStudentId()));

    Submission submission = SubmissionMapper.toSubmissionEntity(submissionDTO);
    submission.setAssignment(assignment);
    submission.setStudent(student);
    submission.setSubmittedAt(LocalDateTime.now());

    Submission savedSubmission = submissionRepository.save(submission);
    return SubmissionMapper.toSubmissionDTO(savedSubmission);
    }

   
    @Override
    @Transactional
    public SubmissionDTO updateSubmission(Long submissionId, SubmissionDTO submissionDTO) {
        Submission existingSubmission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + submissionId));

    existingSubmission.setContentUrl(submissionDTO.getContentUrl());
    existingSubmission.setGrade(submissionDTO.getGrade());
    existingSubmission.setFeedback(submissionDTO.getFeedback());

        Submission updatedSubmission = submissionRepository.save(existingSubmission);
        return SubmissionMapper.toSubmissionDTO(updatedSubmission);
    }

   
    @Override
    @Transactional
    public void deleteSubmission(Long submissionId)  {
        if (!submissionRepository.existsById(submissionId)) {
            throw new ResourceNotFoundException("Submission not found with id: " + submissionId);
        }
        submissionRepository.deleteById(submissionId);
    }

 
    @Override
    @Transactional(readOnly = true)
    public List<SubmissionDTO> getAllSubmissions(){
        return submissionRepository.findAll().stream()
                .map(SubmissionMapper::toSubmissionDTO)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public SubmissionDTO getSubmissionById(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .map(SubmissionMapper::toSubmissionDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + submissionId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubmissionDTO> getSubmissionsByAssignmentId(Long assignmentId) {
        if (!assignmentRepository.existsById(assignmentId)) {
            throw new ResourceNotFoundException("Assignment not found with id: " + assignmentId);
        }
        return submissionRepository.findByAssignment_AssignmentId(assignmentId).stream()
                .map(SubmissionMapper::toSubmissionDTO)
                .collect(Collectors.toList());
    }

    // Assuming these methods should be implemented based on the updated interface
    @Transactional(readOnly = true)
    @Override
    public List<SubmissionDTO> getSubmissionsByDescription(String submissionDescription) {
        // You'll need to add findByDescription to your SubmissionRepository
        return submissionRepository.findByDescription(submissionDescription).stream()
                .map(SubmissionMapper::toSubmissionDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<SubmissionDTO> getSubmissionsByDueDate(LocalDateTime submissionDueDate) {
        // You'll need to add findByDueDate to your SubmissionRepository
        return submissionRepository.findBySubmittedAt(submissionDueDate).stream()
                .map(SubmissionMapper::toSubmissionDTO)
                .collect(Collectors.toList());
    }

    public SubmissionRepository getSubmissionRepository() {
        return submissionRepository;
    }
}
