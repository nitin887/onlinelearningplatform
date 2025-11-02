package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.InstructorDTO;

import java.util.List;

public interface InstructorServiceInterface {
    InstructorDTO createInstructor(InstructorDTO instructorDTO);
    InstructorDTO updateInstructor(Long instructorId, InstructorDTO instructorDTO);
    void deleteInstructor(Long instructorId);
    List<InstructorDTO> getAllInstructors();
    InstructorDTO getInstructorById(Long instructorId);
    InstructorDTO getInstructorByEmail(String email);
}