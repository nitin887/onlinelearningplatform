package com.onlinelearningplatform.learningPlatform.service;

import com.onlinelearningplatform.learningPlatform.dto.InstructorDTO;
import com.onlinelearningplatform.learningPlatform.entity.Instructor;
import com.onlinelearningplatform.learningPlatform.exception.ResourceAlreadyExistsException;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.repository.InstructorRepository;
import com.onlinelearningplatform.learningPlatform.serviceInterface.InstructorServiceInterface;
import com.onlinelearningplatform.learningPlatform.mapper.InstructorMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService implements InstructorServiceInterface {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final PasswordEncoder passwordEncoder;

    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper, PasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        instructorRepository.findByEmail(instructorDTO.getEmail()).ifPresent(i -> {
            throw new ResourceAlreadyExistsException("Instructor with email " + instructorDTO.getEmail() + " already exists.");
        });

        Instructor instructor = instructorMapper.toInstructorEntity(instructorDTO);
        instructor.setInstructorId(null); // Ensure creation of a new entity
        if (instructor.getPassword() != null && !instructor.getPassword().isEmpty()) {
            instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
        }
        Instructor savedInstructor = instructorRepository.save(instructor);
        return instructorMapper.toInstructorDTO(savedInstructor);
    }

    @Override
    @Transactional
    public InstructorDTO updateInstructor(Long instructorId, InstructorDTO instructorDTO) {
        Instructor existingInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + instructorId));

        existingInstructor.setFirstName(instructorDTO.getFirstName());
        existingInstructor.setLastName(instructorDTO.getLastName());
        existingInstructor.setEmail(instructorDTO.getEmail());
        if (instructorDTO.getPassword() != null && !instructorDTO.getPassword().isEmpty()) {
            existingInstructor.setPassword(passwordEncoder.encode(instructorDTO.getPassword()));
        }
        existingInstructor.setBio(instructorDTO.getBio());

        Instructor updatedInstructor = instructorRepository.save(existingInstructor);
        return instructorMapper.toInstructorDTO(updatedInstructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(Long instructorId) {
        if (!instructorRepository.existsById(instructorId)) {
            throw new ResourceNotFoundException("Instructor not found with id: " + instructorId);
        }
        instructorRepository.deleteById(instructorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository.findAll().stream().map(instructorMapper::toInstructorDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorDTO getInstructorById(Long instructorId) {
        return instructorRepository.findById(instructorId).map(instructorMapper::toInstructorDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + instructorId));
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorDTO getInstructorByEmail(String instructorEmail) {
        return instructorRepository.findByEmail(instructorEmail).map(instructorMapper::toInstructorDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with email: " + instructorEmail));
    }
}
