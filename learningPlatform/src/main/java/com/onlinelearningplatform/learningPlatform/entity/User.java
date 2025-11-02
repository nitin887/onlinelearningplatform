package com.onlinelearningplatform.learningPlatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String roles;


    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }
    public String getEmail() {
return email;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }



}
