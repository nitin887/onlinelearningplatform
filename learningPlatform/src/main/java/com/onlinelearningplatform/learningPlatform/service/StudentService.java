package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.StudentDTO;
import com.onlinelearningplatform.learningPlatform.entity.Course;
import com.onlinelearningplatform.learningPlatform.entity.Student;
import com.onlinelearningplatform.learningPlatform.exception.ResourceAlreadyExistsException;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.StudentMapper;
import com.onlinelearningplatform.learningPlatform.repository.CourseRepository;
import com.onlinelearningplatform.learningPlatform.repository.StudentRepository;
import com.onlinelearningplatform.learningPlatform.serviceInterface.StudentServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentServiceInterface {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, StudentMapper studentMapper, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentMapper = studentMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        logger.info("Attempting to create student with email: {}", studentDTO.getEmail());
        studentRepository.findByEmail(studentDTO.getEmail()).ifPresent(s -> {
            logger.error("Student with email {} already exists.", studentDTO.getEmail());
            throw new ResourceAlreadyExistsException("Student with email " + studentDTO.getEmail() + " already exists.");
        });

        Student studentToCreate = studentMapper.toStudentEntity(studentDTO);
        studentToCreate.setStudentId(null);
        if (studentToCreate.getPassword() != null && !studentToCreate.getPassword().isEmpty()) {
            studentToCreate.setPassword(passwordEncoder.encode(studentToCreate.getPassword()));
        }
        Student savedStudent = studentRepository.save(studentToCreate);
        logger.info("Successfully created student with ID: {}", savedStudent.getStudentId());
        return studentMapper.toStudentDTO(savedStudent);
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        existingStudent.setFirstName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setEmail(studentDTO.getEmail());
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isEmpty()) {
            existingStudent.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        }

        if (studentDTO.getEnrolledCourses() != null) {
            List<Long> courseIds = studentDTO.getEnrolledCourses().stream()
                    .map(c -> c.getCourseId())
                    .collect(Collectors.toList());
            List<Course> courses = courseRepository.findAllById(courseIds);
            if(courses.size() != courseIds.size()){
                throw new ResourceNotFoundException("One or more courses not found for enrollment.");
            }
            existingStudent.setEnrolledCourses(courses);
        }

        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toStudentDTO(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long studentId) {
        logger.info("Attempting to find student with ID: {}", studentId);
        return studentRepository.findById(studentId).map(studentMapper::toStudentDTO)
                .orElseThrow(() -> {
                    logger.error("Student not found with ID: {}", studentId);
                    return new ResourceNotFoundException("Student not found with id: " + studentId);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentByEmail(String studentEmail) {
        return studentRepository.findByEmail(studentEmail).map(studentMapper::toStudentDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + studentEmail));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
        return studentRepository.findByEnrolledCourses_CourseId(courseId).stream()
                .map(studentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentByFullName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName).map(studentMapper::toStudentDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with name: " + firstName + " " + lastName));
    }
}
