package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.CourseDTO;
import com.onlinelearningplatform.learningPlatform.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapper {



    public static Course toCourseEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        course.setCourseId(courseDTO.getCourseId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setCategory(courseDTO.getCategory());
        course.setCreatedAt(courseDTO.getCreatedAt());
        // The service layer is responsible for fetching and setting the Instructor entity.
        return course;
    }

    public static CourseDTO toCourseDTO(Course course) {
        if (course == null) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setCategory(course.getCategory());
        courseDTO.setCreatedAt(course.getCreatedAt());

        if (course.getInstructor() != null) {
            courseDTO.setInstructorId(course.getInstructor().getInstructorId());
        }

        if (course.getLessons() != null) {
            courseDTO.setLessons(course.getLessons().stream()
                    .map(LessonMapper::toLessonDTO) // Use injected mapper
                    .collect(Collectors.toList()));
        } else {
            courseDTO.setLessons(Collections.emptyList());
        }

        if (course.getAssignments() != null) {
            courseDTO.setAssignments(course.getAssignments().stream()
                    .map(AssignmentMapper::toAssignmentDTO) // Use injected mapper
                    .collect(Collectors.toList()));
        } else {
            courseDTO.setAssignments(Collections.emptyList());
        }

        return courseDTO;
    }
}
