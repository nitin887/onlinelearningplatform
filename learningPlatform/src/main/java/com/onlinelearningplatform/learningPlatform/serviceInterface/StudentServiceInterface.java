package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.StudentDTO;

import java.util.List;

public interface StudentServiceInterface {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(Long studentId, StudentDTO studentDTO);
    void deleteStudent(Long studentId);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long studentId);
    StudentDTO getStudentByFullName(String firstName, String lastName);
    StudentDTO getStudentByEmail(String studentEmail);
    List<StudentDTO> getStudentsByCourseId(Long courseId);
}