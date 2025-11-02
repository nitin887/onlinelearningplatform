package com.onlinelearningplatform.learningPlatform.mapper;

import com.onlinelearningplatform.learningPlatform.dto.InstructorDTO;
import com.onlinelearningplatform.learningPlatform.entity.Instructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static java.util.stream.Collectors.toList;

@Component
public class InstructorMapper {
    public InstructorDTO toInstructorDTO(Instructor instructor){
        if(instructor==null){
            return  null;
        }
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setInstructorId(instructor.getInstructorId());
        instructorDTO.setFirstName(instructor.getFirstName());
        instructorDTO.setLastName(instructor.getLastName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setBio(instructor.getBio());
        // Note: Intentionally not mapping password to DTO for security reasons.
        if (instructor.getCourses() != null) {
            instructorDTO.setCourses(instructor.getCourses().stream().map(CourseMapper::toCourseDTO).collect(toList()));
        } else {
            instructorDTO.setCourses(Collections.emptyList());
        }

        return instructorDTO;
    }
    public Instructor toInstructorEntity(InstructorDTO instructorDTO){
        if(instructorDTO==null){
            return  null;
        }
        Instructor instructor = new Instructor();
        instructor.setInstructorId(instructorDTO.getInstructorId());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setBio(instructorDTO.getBio());
        instructor.setPassword(instructorDTO.getPassword());
        // Mapping of collections like 'courses' should be handled in the service layer
        // to ensure that managed JPA entities are used instead of transient ones created by the mapper.

        return instructor;

    }
}
