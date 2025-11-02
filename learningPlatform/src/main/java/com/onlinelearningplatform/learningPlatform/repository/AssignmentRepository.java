package com.onlinelearningplatform.learningPlatform.repository;

import com.onlinelearningplatform.learningPlatform.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
Optional<Assignment> findById(Long assignmentId);
List<Assignment> findByTitle(String title);
Optional<Assignment> findByDescription(String description);
List<Assignment> findByDueDate(LocalDateTime dueDate);
List<Assignment> findByCourse_CourseId(Long courseId);
}
