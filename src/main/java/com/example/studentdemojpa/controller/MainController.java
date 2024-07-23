package com.example.studentdemojpa.controller;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.service.NoStudentFoundException;
import com.example.studentdemojpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/")
public class MainController {


    private final StudentService studentService;

    @Autowired
    public MainController(StudentService studentService) {
        this.studentService = studentService;
    }

//    https://www.jetbrains.com/help/objc/exploring-http-syntax.html#dynamic-variables

//    TODO add custom exceptions
//    TODO Move validation to service layer
//    TODO Throw exceptions in service layer
//    TODO catch exceptions in controller
//    TODO have back end generate ID based on max student id + 1

    //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> addNewStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.findAll();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (NoStudentFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentID) {
        try {
            studentService.deleteStudent(studentID);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (NoStudentFoundException ex) {
            return new ResponseEntity<>("No Student Found with Given Student ID", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("No Student ID Given", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json")
    public ResponseEntity<String> updateStudent(@PathVariable("id") int studentID, @RequestBody Student updatedStudent) {
        try {
            studentService.updateStudent(studentID, updatedStudent);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (NoStudentFoundException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByName/{lastName}")
    @ResponseBody
    public List<Student> findStudentsByLastName(@PathVariable("lastName") String lastName) {
        return studentService.findStudentsByLastName(lastName);
    }
}



