package com.psem.relationships.service;

import com.psem.relationships.model.Student;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {


    Student addStudent(Student student, Long schoolId);

    List<Student> getAllStudents();

    void removeStudent(Long studentId);

}
