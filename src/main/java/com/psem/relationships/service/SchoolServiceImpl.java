package com.psem.relationships.service;

import com.psem.relationships.exceptions.ResourceNotFoundException;
import com.psem.relationships.model.School;
import com.psem.relationships.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public School saveSchool(School school) {

        // if statement to call setAddress() method from School model and set connection between two tables.
        if(school.getAddress() != null){
            school.setAddress(school.getAddress());
        }

        // since .save() method returns saved object, saving it to return it to controller.
        School savedSchool = schoolRepository.save(school);

        return savedSchool;
    }

    @Override
    public void deleteSchool(Long schoolId) {

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        schoolRepository.delete(school);
    }

    @Override
    public List getAllSchools() {

        List schools = schoolRepository.findAll();

        if(schools.isEmpty()){
            throw new ResourceNotFoundException("There are no schools added yet.");
        }

        return schools;
    }
}
