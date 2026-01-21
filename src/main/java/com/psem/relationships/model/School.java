package com.psem.relationships.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    @NotBlank
    private String schoolName;

    // Using mappedBy to make relationship with addresses table bidirectional.
    @OneToOne(mappedBy = "school",

                cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Address address;

    // Method to guarantee that bidirectional connection will be set, when School and Address objects are created.
    public void setAddress(Address address){
        address.setSchool(this);
        this.address = address;
    }

    // Orphan removal - remove all students in case school is removed.
    @OneToMany(mappedBy = "school", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE}, orphanRemoval = true)

    private List<Student> students = new ArrayList<>();

}
