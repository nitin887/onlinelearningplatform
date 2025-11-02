package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.CourseDTO;
import com.onlinelearningplatform.learningPlatform.entity.Course;
import com.onlinelearningplatform.learningPlatform.entity.Instructor;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.CourseMapper;
import com.onlinelearningplatform.learningPlatform.repository.CourseRepository;
import com.onlinelearningplatform.learningPlatform.repository.InstructorRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    // Manually added constructor for dependency injection
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + courseDTO.getInstructorId()));

        Course course = CourseMapper.toCourseEntity(courseDTO);
        course.setInstructor(instructor);
        course.setCreatedAt(LocalDateTime.now());

        Course savedCourse = courseRepository.save(course);
        return CourseMapper.toCourseDTO(savedCourse);
    }


    @Transactional(readOnly = true)

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toCourseDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)

    public CourseDTO getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .map(CourseMapper::toCourseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
    }


    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public CourseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        existingCourse.setTitle(courseDTO.getTitle());
        existingCourse.setDescription(courseDTO.getDescription());
        existingCourse.setCategory(courseDTO.getCategory());

        Course updatedCourse = courseRepository.save(existingCourse);
        return CourseMapper.toCourseDTO(updatedCourse);
    }


    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }

    // Assuming these methods should also be secured for all authenticated users

    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public CourseDTO getCourseByTitle(String courseTitle) {
        return courseRepository.findByTitle(courseTitle)
                .map(CourseMapper::toCourseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with title: " + courseTitle));
    }


    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public CourseDTO getCourseByDescription(String courseDescription) {
        return courseRepository.findByDescription(courseDescription)
                .map(CourseMapper::toCourseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with description: " + courseDescription));
    }


    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public CourseDTO getCourseByCategory(String courseCategory) {
        return courseRepository.findByCategory(courseCategory)
                .map(CourseMapper::toCourseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with category: " + courseCategory));
    }

    // Note: getCourseByInstructorId, getCourseByLesson, getCourseByAssignment, getCourseByReview
    // are not present in the provided CourseService.java. If you want to add them,
    // please provide their implementation, and I can add @PreAuthorize to them.
}
