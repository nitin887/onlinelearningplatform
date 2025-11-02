package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.StudentDTO;
import com.onlinelearningplatform.learningPlatform.entity.Student;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class StudentMapper {

    public StudentDTO toStudentDTO(Student student){
        if(student==null){
            return  null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setRole(student.getRole());
        studentDTO.setEmail(student.getEmail());
        // Temporarily disabled for debugging swagger error
        // studentDTO.setEnrolledCourses(student.getEnrolledCourses().stream().map(CourseMapper::toCourseDTO).toList());
        studentDTO.setEnrolledCourses(Collections.emptyList()); // Set to empty list

return  studentDTO;


    }
    public Student toStudentEntity(StudentDTO studentDTO){
        if(studentDTO==null){
            return  null;
        }
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setEmail(studentDTO.getEmail());
        student.setRole(studentDTO.getRole());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        // This direction is less likely to cause a loop, leaving as is for now.
        // The line below is commented out to prevent potential issues with CourseMapper, which is not provided.
        // student.setEnrolledCourses(studentDTO.getEnrolledCourses().stream().map(CourseMapper::toCourseEntity).toList());

        return student;

                 }


}
