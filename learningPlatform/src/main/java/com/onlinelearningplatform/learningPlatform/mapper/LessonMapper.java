package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.LessonDTO;
import com.onlinelearningplatform.learningPlatform.entity.Lesson;

public final class LessonMapper {

    private LessonMapper() {}

    public static LessonDTO toLessonDTO(Lesson lesson) {
        if (lesson == null) {
            return null;
        }
        return new LessonDTO(
                lesson.getLessonId(),
                lesson.getTitle(),
                lesson.getContent(),
                lesson.getVideoUrl()
        );
    }

    public static Lesson toLessonEntity(LessonDTO lessonDTO) {
        if (lessonDTO == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonDTO.getLessonId());
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setContent(lessonDTO.getContent());
        lesson.setVideoUrl(lessonDTO.getVideoUrl());
        return lesson;
    }
}