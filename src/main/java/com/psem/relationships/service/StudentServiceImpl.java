package com.psem.relationships.service;

import com.psem.relationships.exceptions.ResourceNotFoundException;
import com.psem.relationships.model.School;
import com.psem.relationships.model.Student;
import com.psem.relationships.repository.SchoolRepository;
import com.psem.relationships.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @Override
    @Transactional // If school doesn't exist, roll back saving user to DB.
    public Student addStudent(Student student, Long schoolId) {

        Student savedStudent = studentRepository.save(student);

        School school = schoolRepository.findById(schoolId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("School with id: " + schoolId + " doesn't exist"));

        savedStudent.setSchool(school);

        return savedStudent;
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()) throw new ResourceNotFoundException("No students added yet.");

        return students;
    }

    @Override
    public void removeStudent(Long studentId) {

        Student deletedStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id: " + studentId + "not found"));

        studentRepository.delete(deletedStudent);
    }
}
