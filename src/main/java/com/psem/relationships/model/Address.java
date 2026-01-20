package com.psem.relationships.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min=3, message = "City name must be at least 3 characters long")
    private String city;

    @NotBlank
    @Size(min=6, message = "Street name must be at least 6 characters long")
    private String street;

    @NotBlank
    @Size(min=3, message = "Country name must be at least 3 characters long")
    private String country;

    @NotBlank
    @Size(min=5, message = "ZIP code must be contain at least 5 numbers")
    private String zipCode;

    @OneToOne
    // @JoinColumn(name) annotation to set column name in addresses table
    @JoinColumn(name = "school_id", referencedColumnName = "schoolId")
    // @JsonIgnore to prevent from infinite JSON loop.
    @JsonIgnore
    private School school;


}
