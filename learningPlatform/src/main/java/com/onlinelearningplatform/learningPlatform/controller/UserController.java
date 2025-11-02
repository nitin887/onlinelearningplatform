package com.onlinelearningplatform.learningPlatform.controller;

import com.onlinelearningplatform.learningPlatform.dto.UserDTO;
import com.onlinelearningplatform.learningPlatform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
 // Require USER role for all methods in this controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO UserAdmin= userService.createUser(userDTO);
        return new ResponseEntity<>(UserAdmin, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @SecurityRequirement(name = "bearerAuth")

    @PutMapping("/id/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long UserId){
        UserDTO updatingUser = userService.createUser( userDTO);
        return ResponseEntity.ok(updatingUser);
    }

    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")



    @DeleteMapping("/id/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all users")
    })

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")

    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> allUser= userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })

    @GetMapping("/id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")



    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        UserDTO UserById= userService.getUserById(userId);
        return ResponseEntity.ok(UserById);
    }

    @Operation(summary = "Get a user by name")
    @ApiResponses(value = {
               @ApiResponse(responseCode = "200", description = "Found the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")



    @GetMapping("/by-name/{userName}")

    public ResponseEntity<UserDTO> getUserByName(@PathVariable String userName){
        UserDTO userByName= userService.getUserByName(userName);
        return ResponseEntity.ok(userByName);
    }
}
