package com.psem.relationships.service;

import com.psem.relationships.model.School;
import jakarta.validation.Valid;

import java.util.List;

public interface SchoolService {

    School saveSchool(School school);

    void deleteSchool(Long schoolId);

    List getAllSchools();
}
