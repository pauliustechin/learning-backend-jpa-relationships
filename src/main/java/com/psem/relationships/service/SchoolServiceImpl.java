package com.psem.relationships.service;

import com.psem.relationships.exceptions.ResourceNotFoundException;
import com.psem.relationships.model.School;
import com.psem.relationships.payload.AddressDTO;
import com.psem.relationships.payload.SchoolDTO;
import com.psem.relationships.payload.SchoolDTO2;
import com.psem.relationships.payload.SchoolResponse;
import com.psem.relationships.repository.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SchoolDTO saveSchool(School school) {

        // if statement to call setAddress() method from School model and set connection between two tables.
        if(school.getAddress() != null){
            school.setAddress(school.getAddress());
        }

        School savedSchool = schoolRepository.save(school);

        AddressDTO addressDTO = modelMapper.map(savedSchool.getAddress(), AddressDTO.class);
        SchoolDTO schoolDTO = modelMapper.map(school, SchoolDTO.class);
        schoolDTO.setAddressDTO(addressDTO);

        return schoolDTO;
    }

    @Override
    public void deleteSchool(Long schoolId) {

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        schoolRepository.delete(school);
    }

    @Override
    public SchoolResponse getAllSchools() {

        List<School> schools = schoolRepository.findAll();

        if(schools.isEmpty()){
            throw new ResourceNotFoundException("There are no schools added yet.");
        }


        List<SchoolDTO2> schoolDTOs = schools.stream()
                .map(school -> modelMapper.map(school, SchoolDTO2.class))
                .toList();

        SchoolResponse schoolResponse = new SchoolResponse();
        schoolResponse.setContent(schoolDTOs);

        return schoolResponse;
    }
}
