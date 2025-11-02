package com.onlinelearningplatform.learningPlatform.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private List<CourseDTO> enrolledCourses = new ArrayList<>();
    private List<AssignmentDTO> assignments = new ArrayList<>();
    private List<LessonDTO> lessons = new ArrayList<>();
    private List<InstructorDTO> instructors = new ArrayList<>();
    private List<SubmissionDTO> submissions = new ArrayList<>();

    public StudentDTO() {
    }

    public StudentDTO(Long studentId, String firstName, String lastName, String email, String password, String role, List<CourseDTO> enrolledCourses, List<AssignmentDTO> assignments, List<LessonDTO> lessons, List<InstructorDTO> instructors, List<SubmissionDTO> submissions) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enrolledCourses = enrolledCourses;
        this.assignments = assignments;
        this.lessons = lessons;
        this.instructors = instructors;
        this.submissions = submissions;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CourseDTO> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<CourseDTO> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<AssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentDTO> assignments) {
        this.assignments = assignments;
    }

    public List<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDTO> lessons) {
        this.lessons = lessons;
    }

    public List<InstructorDTO> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorDTO> instructors) {
        this.instructors = instructors;
    }

    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }
}
