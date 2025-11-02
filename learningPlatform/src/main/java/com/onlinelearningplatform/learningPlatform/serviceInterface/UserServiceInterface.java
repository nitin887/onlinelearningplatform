package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.UserDTO;

import java.util.List;

public interface UserServiceInterface {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    void deleteUser(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long userId);

    UserDTO getUserByName(String userName);

    UserDTO getUserByEmail(String userEmail);
}
