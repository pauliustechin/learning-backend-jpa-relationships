package com.psem.relationships.controller;

import com.psem.relationships.model.Program;
import com.psem.relationships.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Tag(name="Educational programs APIs", description = "APIs for managing educational programs")
    @Operation(summary = "Create new educational program")
    @PostMapping("/schools/{schoolId}/programs")
    public ResponseEntity<Program> saveProgram(@RequestBody Program program,
                                              @PathVariable Long schoolId){

        Program savedProgram = programService.addProgram(program, schoolId);

        return new ResponseEntity<>(program, HttpStatus.CREATED);
    }

    @Tag(name="Educational programs APIs")
    @Operation(summary = "Delete educational program")
    @DeleteMapping("/programs/{programId}")
    public ResponseEntity<String> deleteProgram(@PathVariable Long programId){

        programService.deleteProgram(programId);

        return new ResponseEntity<>("Program with Id:" + programId + " deleted successfully", HttpStatus.OK);

    }

}
