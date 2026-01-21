package com.psem.relationships.controller;

import com.psem.relationships.model.Student;
import com.psem.relationships.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/schools/{schoolId}/students")
    private ResponseEntity<Student> addStudent(@Valid @RequestBody Student student,
                                               @PathVariable Long schoolId){

        Student savedStudent = studentService.addStudent(student, schoolId);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

    }

    @GetMapping("/public/students")
    private ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.getAllStudents();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/public/students/{studentId}")
    public ResponseEntity<String> removeStudent(@PathVariable Long studentId){

        studentService.removeStudent(studentId);

        return new ResponseEntity<>("Student removed successfully", HttpStatus.OK);
    }

}
