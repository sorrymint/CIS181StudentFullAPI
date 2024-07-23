package com.example.studentdemojpa.repository;

import com.example.studentdemojpa.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByLastName(String lastName);
    Optional<Student> findStudentByStudentID(int studentID);
}
