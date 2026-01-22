package com.psem.relationships.payload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// implement DTO (data transfer objects) to control flow of passed information.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {

    private Long schoolId;
    private String schoolName;
    private AddressDTO addressDTO;
}
