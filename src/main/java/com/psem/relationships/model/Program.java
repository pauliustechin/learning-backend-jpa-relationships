package com.psem.relationships.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "educational_programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long programId;

    @NotBlank
    private String programName;

    @JsonIgnore
    @ManyToMany(mappedBy = "programs")
    private Set<Student> students = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(programId);
    }

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonIgnore // exclude from swagger documentation
    private School school;
}
