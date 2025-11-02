package com.onlinelearningplatform.learningPlatform;

import com.onlinelearningplatform.learningPlatform.dto.StudentDTO;
import com.onlinelearningplatform.learningPlatform.entity.Student;
import com.onlinelearningplatform.learningPlatform.exception.ResourceNotFoundException;
import com.onlinelearningplatform.learningPlatform.mapper.StudentMapper;
import com.onlinelearningplatform.learningPlatform.repository.CourseRepository;
import com.onlinelearningplatform.learningPlatform.repository.StudentRepository;
import com.onlinelearningplatform.learningPlatform.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getStudentById_Success() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setStudentId(studentId);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
        student.setPassword("password");
        student.setRole("STUDENT");

        StudentDTO expectedStudentDTO = new StudentDTO();
        expectedStudentDTO.setStudentId(studentId);
        expectedStudentDTO.setFirstName("John");
        expectedStudentDTO.setEmail("john.doe@example.com");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentDTO(student)).thenReturn(expectedStudentDTO);

        // When
        StudentDTO actualStudentDTO = studentService.getStudentById(studentId);

        // Then
        assertNotNull(actualStudentDTO);
        assertEquals(expectedStudentDTO.getStudentId(), actualStudentDTO.getStudentId());
        assertEquals(expectedStudentDTO.getFirstName(), actualStudentDTO.getFirstName());
        assertEquals(expectedStudentDTO.getEmail(), actualStudentDTO.getEmail());
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentMapper, times(1)).toStudentDTO(student);
    }

    @Test
    void getStudentById_NotFound() {
        // Given
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudentById(studentId);
        });

        assertEquals("Student not found with id: " + studentId, thrown.getMessage());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void updateStudent_Success() {
        // Given
        Long studentId = 1L;
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(studentId);
        studentDTO.setFirstName("Jane");
        studentDTO.setEmail("jane.doe@example.com");

        Student existingStudent = new Student();
        existingStudent.setStudentId(studentId);
        existingStudent.setFirstName("John");
        existingStudent.setEmail("john.doe@example.com");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(existingStudent); // Mock save to return the updated student
        when(studentMapper.toStudentDTO(any(Student.class))).thenReturn(studentDTO); // Mock toStudentDTO to return the updated DTO

        // When
        StudentDTO updatedStudentDTO = studentService.updateStudent(studentId, studentDTO);

        // Then
        assertNotNull(updatedStudentDTO);
        assertEquals("Jane", updatedStudentDTO.getFirstName());
        assertEquals("jane.doe@example.com", updatedStudentDTO.getEmail());
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).save(existingStudent);
        verify(studentMapper, times(1)).toStudentDTO(existingStudent);
    }

    @Test
    void updateStudent_NotFound() {
        // Given
        Long studentId = 1L;
        StudentDTO studentDTO = new StudentDTO();
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> studentService.updateStudent(studentId, studentDTO));
        verify(studentRepository, times(1)).findById(studentId);
    }
}
