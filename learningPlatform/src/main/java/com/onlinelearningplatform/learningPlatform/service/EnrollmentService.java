package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.EnrollmentDTO;
import com.onlinelearningplatform.learningPlatform.entity.Course;
import com.onlinelearningplatform.learningPlatform.entity.Enrollment;
import com.onlinelearningplatform.learningPlatform.entity.Student;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.EnrollmentMapper;
import com.onlinelearningplatform.learningPlatform.repository.CourseRepository;
import com.onlinelearningplatform.learningPlatform.repository.EnrollmentRepository;
import com.onlinelearningplatform.learningPlatform.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // Manually added constructor for dependency injection
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + enrollmentDTO.getStudentId()));

        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + enrollmentDTO.getCourseId()));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.toEnrollmentDTO(savedEnrollment);
    }

    @Transactional(readOnly = true)
    public EnrollmentDTO getEnrollmentById(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .map(EnrollmentMapper::toEnrollmentDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteEnrollment(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId);
        }
        enrollmentRepository.deleteById(enrollmentId);
    }
}
