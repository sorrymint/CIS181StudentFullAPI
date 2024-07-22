package com.example.studentdemojpa.service;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

//    @PersistenceContext
//    private EntityManager entityManager;


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> findStudentById(Long id){
        return studentRepository.findById(id);
    }


    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setEmail(updatedStudent.getEmail());
            student.setEContact(updatedStudent.getEContact());
            return studentRepository.save(student);
        }).orElse(null);
    }

    @Override
    public List<Student> findStudentsByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }
}
