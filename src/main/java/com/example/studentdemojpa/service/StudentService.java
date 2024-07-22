package com.example.studentdemojpa.service;

import com.example.studentdemojpa.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll() throws NoStudentFoundException;
    void saveStudent(Student student);
    void deleteStudent(Long id);
    Student updateStudent(Long id, Student updatedStudent);
    List<Student> findStudentsByLastName(String lastName);
    Optional<Student> findStudentById(Long id);
}

