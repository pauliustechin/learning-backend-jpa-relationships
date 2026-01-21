package com.psem.relationships.service;

import com.psem.relationships.exceptions.ResourceNotFoundException;
import com.psem.relationships.model.Program;
import com.psem.relationships.model.School;
import com.psem.relationships.repository.ProgramRepository;
import com.psem.relationships.repository.SchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImpl implements ProgramService{

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    @Transactional
    public Program addProgram(Program program, Long schoolId) {

        Program savedProgram = programRepository.save(program);

        School school = schoolRepository.findById(schoolId)
                        .orElseThrow(() -> new ResourceNotFoundException("School with ID: " + schoolId + " not found"));

        savedProgram.setSchool(school);

        return savedProgram;
    }

    @Override
    public void deleteProgram(Long programId) {

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ResourceNotFoundException("Program with ID: " + programId + " not found"));

        programRepository.delete(program);

    }
}
