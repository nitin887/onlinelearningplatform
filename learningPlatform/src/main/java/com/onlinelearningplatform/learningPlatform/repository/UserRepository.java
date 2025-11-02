package com.onlinelearningplatform.learningPlatform.repository;

import com.onlinelearningplatform.learningPlatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional< User> findById(Long adminId);
    Optional< User> findByEmail(String email);
    Optional< User> findByUsername(String username);


}
