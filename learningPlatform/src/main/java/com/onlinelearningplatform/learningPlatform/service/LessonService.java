package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.LessonDTO;
import com.onlinelearningplatform.learningPlatform.entity.Course;
import com.onlinelearningplatform.learningPlatform.entity.Lesson;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.LessonMapper;
import com.onlinelearningplatform.learningPlatform.repository.CourseRepository;
import com.onlinelearningplatform.learningPlatform.repository.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    // Manually added constructor for dependency injection
    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public LessonDTO createLesson(Long courseId, LessonDTO lessonDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        Lesson lesson = LessonMapper.toLessonEntity(lessonDTO);
        lesson.setCourse(course);

        Lesson savedLesson = lessonRepository.save(lesson);
        return LessonMapper.toLessonDTO(savedLesson);
    }

    @Transactional(readOnly = true)
    public List<LessonDTO> getLessonsByCourseId(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        }
        return lessonRepository.findByCourse_CourseId(courseId).stream()
                .map(LessonMapper::toLessonDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LessonDTO getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .map(LessonMapper::toLessonDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
    }

    @Transactional
    public void deleteLesson(Long lessonId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new ResourceNotFoundException("Lesson not found with id: " + lessonId);
        }
        lessonRepository.deleteById(lessonId);
    }
}
