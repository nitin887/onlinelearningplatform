package com.onlinelearningplatform.learningPlatform.repository;

import com.onlinelearningplatform.learningPlatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCategory(String courseCategory);

    Optional<Course> findByTitle(String courseTitle);

    Optional<Course> findByDescription(String courseDescription);
}