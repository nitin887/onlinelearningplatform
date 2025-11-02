package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.AssignmentDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface AssignmentServiceInterface {

    AssignmentDTO createAssignment(AssignmentDTO assignmentDTO);

    AssignmentDTO updateAssignment(Long assignmentId, AssignmentDTO assignmentDTO);

    void deleteAssignment(Long assignmentId);

    List<AssignmentDTO> getAllAssignments();

    AssignmentDTO getAssignmentById(Long assignmentId);

    List<AssignmentDTO> getAssignmentsByTitle(String assignmentTitle);

    List<AssignmentDTO> getAssignmentsByDescription(String assignmentDescription);

    List<AssignmentDTO> getAssignmentsByCourseId(Long courseId);

    List<AssignmentDTO> getAssignmentsByDueDate(LocalDateTime assignmentDueDate);
}
