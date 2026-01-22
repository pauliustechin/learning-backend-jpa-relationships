package com.psem.relationships.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @ManyToOne
    @JoinColumn(name="school_id")
    @JsonIgnore
    private School school;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    // set table name and column names for a cross table.
    @JoinTable(
            name="student_programs",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="program_id")
    )
    private Set<Program> programs = new HashSet<>();

    // add helper method to delete entities from owner side.
    public void removeProgram(Program program){
        this.programs.remove(program);
        program.getStudents().remove(this);
    }

    @Override
    public int hashCode(){
        return Objects.hash(studentId);
    }

}
