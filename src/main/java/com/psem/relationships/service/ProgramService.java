package com.psem.relationships.service;

import com.psem.relationships.model.Program;

public interface ProgramService {
    Program addProgram(Program program, Long schoolId);

    void deleteProgram(Long programId);
}
