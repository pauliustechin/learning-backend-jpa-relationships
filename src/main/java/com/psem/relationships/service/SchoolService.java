package com.psem.relationships.service;

import com.psem.relationships.model.School;
import com.psem.relationships.payload.SchoolDTO;
import com.psem.relationships.payload.SchoolResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface SchoolService {

    SchoolDTO saveSchool(School school);

    void deleteSchool(Long schoolId);

    SchoolResponse getAllSchools();
}
