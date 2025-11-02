package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.AssignmentDTO;
import com.onlinelearningplatform.learningPlatform.entity.Assignment;
import com.onlinelearningplatform.learningPlatform.entity.Course;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

    public static AssignmentDTO toAssignmentDTO(Assignment assignment) {
        if (assignment == null) {
            return null;
        }
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setAssignmentId(assignment.getAssignmentId());
        // Correctly map courseId from the associated Course entity
        if (assignment.getCourse() != null) {
            assignmentDTO.setCourseId(assignment.getCourse().getCourseId());
        }
        assignmentDTO.setTitle(assignment.getTitle());
        assignmentDTO.setDescription(assignment.getDescription());
        assignmentDTO.setDueDate(assignment.getDueDate());
        // Assuming submissions are mapped separately or lazy loaded
        assignmentDTO.setSubmissions(assignment.getSubmissions().stream().map(SubmissionMapper::toSubmissionDTO).collect(Collectors.toList()));
        return assignmentDTO;
    }

    public static Assignment toAssignmentEntity(AssignmentDTO assignmentDTO) {
        if (assignmentDTO == null) {
            return null;
        }
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(assignmentDTO.getAssignmentId());
        // Correctly set the Course entity from courseId
        if (assignmentDTO.getCourseId() != null) {
            Course course = new Course();
            course.setCourseId(assignmentDTO.getCourseId());
            assignment.setCourse(course);
        }
        assignment.setTitle(assignmentDTO.getTitle());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(assignmentDTO.getDueDate());
        return assignment;
    }
}
