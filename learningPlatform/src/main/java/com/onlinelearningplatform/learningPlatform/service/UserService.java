package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.UserDTO;
import com.onlinelearningplatform.learningPlatform.entity.User;
import com.onlinelearningplatform.learningPlatform.exception.UserAlreadyExistException;
import com.onlinelearningplatform.learningPlatform.exception.UserNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.UserMapper;
import com.onlinelearningplatform.learningPlatform.repository.UserRepository;
import com.onlinelearningplatform.learningPlatform.serviceInterface.UserServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userRepository.findById(userDTO.getUserId()).ifPresent(c -> {
            throw new UserAlreadyExistException("User with ID " + userDTO.getUserId() + " already exists.");
        });
        User creationOfUser = userMapper.toEntity(userDTO);
        creationOfUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode password
        creationOfUser = userRepository.save(creationOfUser);
        return userMapper.toDto(creationOfUser);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode password
        }
        existingUser.setRoles(userDTO.getRole());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
    }

    @Override
    public UserDTO getUserByName(String userName) {
        return userRepository.findByUsername(userName)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with username " + userName + " not found."));
    }

    @Override
    public UserDTO getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with email " + userEmail + " not found."));
    }
}
