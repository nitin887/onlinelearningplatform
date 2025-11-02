package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.EnrollmentDTO;
import com.onlinelearningplatform.learningPlatform.entity.Enrollment;

public class EnrollmentMapper {

    public static EnrollmentDTO toEnrollmentDTO(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getEnrollmentId(),
                enrollment.getStudent().getStudentId(),
                enrollment.getCourse().getCourseId(),
                enrollment.getEnrollmentDate()
        );
    }

    public static Enrollment toEnrollmentEntity(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentDTO.getEnrollmentId());
        enrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
        // Note: Setting student and course requires fetching them from the database
        // This will be handled in the service layer
        return enrollment;
    }
}
