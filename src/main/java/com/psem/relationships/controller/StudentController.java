package com.psem.relationships.controller;

import com.psem.relationships.model.Student;
import com.psem.relationships.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name="Students APIs", description = "APIs for managing students")
    @Operation(summary = "Add new student")
    @PostMapping("/schools/{schoolId}/students")
    private ResponseEntity<Student> addStudent(@Valid @RequestBody Student student,
                                               @PathVariable Long schoolId){

        Student savedStudent = studentService.addStudent(student, schoolId);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

    }

    @Tag(name="Students APIs")
    @Operation(summary = "Get all students")
    @GetMapping("/students")
    private ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.getAllStudents();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Tag(name="Students APIs")
    @Operation(summary = "Delete student")
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> removeStudent(@PathVariable Long studentId){

        studentService.removeStudent(studentId);

        return new ResponseEntity<>("Student removed successfully", HttpStatus.OK);
    }

    @Tag(name="Students APIs")
    @Operation(summary = "Add educational program for a student")
    @PostMapping("/students/{studentId}/programs/{programId}")
    public ResponseEntity<String> addProgramForStudent(@PathVariable Long studentId,
                                                       @PathVariable Long programId) {

        studentService.addProgramForStudent(studentId, programId);

        return new ResponseEntity<>("Program added successfully for student with ID: " + studentId, HttpStatus.OK);
    }
}
