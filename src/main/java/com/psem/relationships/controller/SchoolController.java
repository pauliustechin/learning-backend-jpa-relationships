package com.psem.relationships.controller;

import com.psem.relationships.model.School;
import com.psem.relationships.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/schools/{schoolId}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long schoolId){

        schoolService.deleteSchool(schoolId);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/schools")
    public ResponseEntity<List<School>> getAllSchools(){

        List schools = schoolService.getAllSchools();

        return new ResponseEntity<>(schools, HttpStatus.OK);
    }

}
