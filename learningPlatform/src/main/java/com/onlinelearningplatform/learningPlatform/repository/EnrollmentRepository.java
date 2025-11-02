package com.onlinelearningplatform.learningPlatform.repository;

import com.onlinelearningplatform.learningPlatform.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
