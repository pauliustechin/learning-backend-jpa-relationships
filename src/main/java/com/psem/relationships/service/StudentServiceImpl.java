package com.psem.relationships.service;

import com.psem.relationships.exceptions.ResourceNotFoundException;
import com.psem.relationships.model.Program;
import com.psem.relationships.model.School;
import com.psem.relationships.model.Student;
import com.psem.relationships.repository.ProgramRepository;
import com.psem.relationships.repository.SchoolRepository;
import com.psem.relationships.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ProgramRepository programRepository;

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

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id: " + studentId + "not found"));

        // before deleting student, removing all associated programs in junction table.
        student.getPrograms().forEach(program -> {
            student.removeProgram(program);
        });

        // then deleting a student.
        studentRepository.delete(student);
    }

    @Override
    public void addProgramForStudent(Long studentId, Long programId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id: " + studentId + "not found"));

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ResourceNotFoundException("Program with id: " + programId + "not found"));

        // get programs list which belongs to a student.
        Set<Program> studentPrograms = student.getPrograms();

        // since studentPrograms is Set no need to check, if program already exists.
        studentPrograms.add(program);
        student.setPrograms(studentPrograms);

        // save updated Student
        studentRepository.save(student);
    }
}
