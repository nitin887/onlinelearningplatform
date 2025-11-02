package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.AssignmentDTO;
import com.onlinelearningplatform.learningPlatform.entity.Assignment;
import com.onlinelearningplatform.learningPlatform.entity.Course;
import com.onlinelearningplatform.learningPlatform.exception.ResourceAlreadyExistsException;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.AssignmentMapper;
import com.onlinelearningplatform.learningPlatform.repository.AssignmentRepository;
import com.onlinelearningplatform.learningPlatform.repository.CourseRepository;
import com.onlinelearningplatform.learningPlatform.serviceInterface.AssignmentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService implements AssignmentServiceInterface {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final AssignmentMapper assignmentMapper;
    // Injected as a bean
    public AssignmentService(AssignmentRepository assignmentRepository, CourseRepository courseRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    @Transactional
    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        // Check if an assignment with the same title already exists to prevent duplicates.
        assignmentRepository.findByTitle(assignmentDTO.getTitle()).stream().findAny().ifPresent(a -> {
            throw new ResourceAlreadyExistsException("Assignment with title '" + assignmentDTO.getTitle() + "' already exists.");
        });

        Course course = courseRepository.findById(assignmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + assignmentDTO.getCourseId()));

        Assignment assignment = AssignmentMapper.toAssignmentEntity(assignmentDTO);
        assignment.setCourse(course);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return AssignmentMapper.toAssignmentDTO(savedAssignment);
    }

    @Override
    @Transactional
    public AssignmentDTO updateAssignment(Long assignmentId, AssignmentDTO assignmentDTO) {
        Assignment existingAssignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + assignmentId));

        existingAssignment.setTitle(assignmentDTO.getTitle());
        existingAssignment.setDescription(assignmentDTO.getDescription());
        existingAssignment.setDueDate(assignmentDTO.getDueDate());

        Assignment updatedAssignment = assignmentRepository.save(existingAssignment);
        return AssignmentMapper.toAssignmentDTO(updatedAssignment);
    }

    @Override
    @Transactional
    public void deleteAssignment(Long assignmentId) {
        if (!assignmentRepository.existsById(assignmentId)) {
            throw new ResourceNotFoundException("Assignment not found with id: " + assignmentId);
        }
        assignmentRepository.deleteById(assignmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentDTO> getAllAssignments() {
        return assignmentRepository.findAll().stream()
                .map(AssignmentMapper::toAssignmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AssignmentDTO getAssignmentById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId)
                .map(AssignmentMapper::toAssignmentDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + assignmentId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentDTO> getAssignmentsByTitle(String assignmentTitle) {
        return assignmentRepository.findByTitle(assignmentTitle).stream()
                .map(AssignmentMapper::toAssignmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentDTO> getAssignmentsByDescription(String assignmentDescription) {
        return assignmentRepository.findByDescription(assignmentDescription).stream()
                .map(AssignmentMapper::toAssignmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentDTO> getAssignmentsByCourseId(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        }
        return assignmentRepository.findByCourse_CourseId(courseId).stream()
                .map(AssignmentMapper::toAssignmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentDTO> getAssignmentsByDueDate(LocalDateTime dueDate) {
        return assignmentRepository.findByDueDate(dueDate).stream()
                .map(AssignmentMapper::toAssignmentDTO)
                .collect(Collectors.toList());
    }
}
