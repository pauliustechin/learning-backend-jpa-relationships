package com.psem.relationships.controller;

import com.psem.relationships.model.School;
import com.psem.relationships.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    // @Valid annotation, to check added validation in School model
    @PostMapping("/schools")
    public ResponseEntity<School> saveSchool(@Valid @RequestBody School school){

        School savedSchool = schoolService.saveSchool(school);

        // Return saved object with HttpStatus.
        return new ResponseEntity<>(savedSchool, HttpStatus.CREATED);
    }

}
