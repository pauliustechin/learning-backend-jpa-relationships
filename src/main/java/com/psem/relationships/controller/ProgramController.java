package com.psem.relationships.controller;

import com.psem.relationships.model.Program;
import com.psem.relationships.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @PostMapping("/schools/{schoolId}/programs")
    public ResponseEntity<Program> saveProgram(@RequestBody Program program,
                                              @PathVariable Long schoolId){

        Program savedProgram = programService.addProgram(program, schoolId);

        return new ResponseEntity<>(program, HttpStatus.CREATED);
    }

    @DeleteMapping("/programs/{programId}")
    public ResponseEntity<String> deleteProgram(@PathVariable Long programId){

        programService.deleteProgram(programId);

        return new ResponseEntity<>("Program with Id:" + programId + " deleted successfully", HttpStatus.OK);

    }

}
