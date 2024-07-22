package com.example.studentdemojpa.controller;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.service.NoStudentFoundException;
import com.example.studentdemojpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/")
public class MainController {


    private final StudentService studentService;


    @Autowired
    public MainController(StudentService studentService){
        this.studentService = studentService;
    }

//    https://www.jetbrains.com/help/objc/exploring-http-syntax.html#dynamic-variables

//    TODO add custom excitptions
//    Move valdation to service layer
//    Throw expections in service layer
//    catch expdctions in controller

    //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> addNewStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        try{
            List<Student> students = studentService.findAll();
            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch(NoStudentFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
        try {
            Optional<Student> optionalStudent = studentService.findStudentById(id);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                studentService.deleteStudent(id);
                return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
            }
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id, @RequestBody Student updatedStudent) {
        Student updated = studentService.updateStudent(id, updatedStudent);
        if (updated != null) {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByName/{lastName}")
    @ResponseBody
    public List<Student> findStudentsByLastName(@PathVariable("lastName") String lastName) {
        return studentService.findStudentsByLastName(lastName);
    }
}



