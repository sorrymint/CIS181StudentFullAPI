package com.example.studentdemojpa.service;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.model.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    List<Student> findAll() throws NoStudentFoundException;
    void saveStudent(Student student);
    void deleteStudent(long studentID) throws NoStudentFoundException;
    void updateStudent(long studentID, StudentUpdateDto updatedStudent) throws NoStudentFoundException;
    List<Student> findStudentsByLastName(String lastName);
}

