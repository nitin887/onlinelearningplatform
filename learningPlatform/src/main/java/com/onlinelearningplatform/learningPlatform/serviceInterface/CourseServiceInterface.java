package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.CourseDTO;

import java.util.List;

public interface CourseServiceInterface {
    //create course by id
    CourseDTO creatingCourseById(CourseDTO courseDTO);
    //update course by id
    CourseDTO updatingCourseById(Long courseId, CourseDTO courseDTO);
    //delete course by id
    CourseDTO deletingCourseById(Long courseId);


    //get all course
    List<CourseDTO> getAllCourse();
    //get course by id
    CourseDTO getCourseById(Long courseId);
    //get course by title
    CourseDTO getCourseByTitle(String courseTitle);
    //get course by description
    CourseDTO getCourseByDescription(String courseDescription);
    //get course by category
    CourseDTO getCourseByCategory(String courseCategory);

    //get course by lesson
    CourseDTO getCourseByLessonId(Long lessonId);


    //get course by instructor id
    CourseDTO getCourseByInstructorId(Long instructorId);



    //get course by Assignment
    CourseDTO getCourseByAssignmentId(Long assignmentId);

    //get course by review
    CourseDTO getCourseByReview(String courseReview);




}
