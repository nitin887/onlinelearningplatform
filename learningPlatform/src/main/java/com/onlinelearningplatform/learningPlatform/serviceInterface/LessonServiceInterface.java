package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.LessonDTO;

import java.util.List;

public interface LessonServiceInterface {
    //create lesson by id
    LessonDTO creatingLessonById( LessonDTO lessonDTO);
    //update lesson by id
    LessonDTO updatingLessonById(Long lessonId, LessonDTO lessonDTO);
    //delete lesson by id
    LessonDTO deletingLessonById(Long lessonId);

    //get all lesson
    List<LessonDTO> getAllLesson();
    //get lesson by id
    LessonDTO getLessonById(Long lessonId);
    //get lesson by title
    LessonDTO getLessonByTitle(String lessonTitle);
    //get lesson by content
    LessonDTO getLessonByContent(String lessonContent);
    //get lesson by video url
    LessonDTO getLessonByVideoUrl(String lessonVideoUrl);
    //get lesson by course id
    LessonDTO getLessonByCourseId(Long courseId);
    //get lesson by assignment
    LessonDTO getLessonByAssignmentId(Long assignmentId);
    //get lesson by review
    LessonDTO getLessonByReview(String lessonReview);



}
