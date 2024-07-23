package com.example.studentdemojpa.service;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.repository.StudentRepository;
import jakarta.validation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

//    @PersistenceContext
//    private EntityManager entityManager;


    private final StudentRepository studentRepository;
    private final Validator validator; // Inject Validator

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public List<Student> findAll() throws NoStudentFoundException {
        List<Student> students =  (List<Student>) studentRepository.findAll();
        if(students.isEmpty()){
            throw  new NoStudentFoundException("No Students found");
        }
        return students;
    }

    @Override
    public void saveStudent(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed", violations);
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int studentId) throws NoStudentFoundException {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid ID provided.");
        }
        Optional<Student> s = studentRepository.findStudentByStudentID(studentId);
        if (s.isEmpty()) {
            throw new NoStudentFoundException("Character not found.");
        }
        studentRepository.delete(s.get());
    }

//    @Override
//    public Optional<Student> findStudentById(Long id){
//        return studentRepository.findById(id);
//    }


    @Override
    public Student updateStudent(int studentID, Student updatedStudent) throws NoStudentFoundException {
        Optional<Student> existingStudent = studentRepository.findStudentByStudentID(studentID);
        if (existingStudent.isEmpty()) {
            throw new NoStudentFoundException("Student not found.");
        }
        Set<ConstraintViolation<Student>> violations = validator.validate(updatedStudent);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed", violations);
        }
        // Assuming you want to merge the existing student with the updated values
        existingStudent.ifPresent(student -> {
            student.setEmail(updatedStudent.getEmail());
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEContact(updatedStudent.getEContact());
            studentRepository.save(student);
        });
        return existingStudent.orElseThrow(() -> new NoStudentFoundException("Student not found."));
    }

    @Override
    public List<Student> findStudentsByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }
}
