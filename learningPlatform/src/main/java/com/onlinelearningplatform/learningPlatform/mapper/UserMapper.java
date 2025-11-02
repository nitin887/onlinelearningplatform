package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.UserDTO;
import com.onlinelearningplatform.learningPlatform.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        // Password should generally not be exposed in DTOs for security reasons.
        // If needed for specific operations (e.g., password change), create a separate DTO.
        // For now, we'll omit it from the general toDto mapping.
        // adminDTO.setPassword(admin.getPassword());
        userDTO.setRole(user.getRoles());
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRole());
        return user;
    }
}
