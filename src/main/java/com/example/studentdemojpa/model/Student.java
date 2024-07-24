package com.example.studentdemojpa.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    @JsonProperty("studentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long studentID;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Must be valid email")
    @Column(name = "email")
    private String email;

    @Column(name = "emergency_contact")
    @Pattern(regexp = "^[0-9]{10}$", message = "Must be exactly 10 digits")
    @JsonProperty("eContact") //needed to map column name to property name
    private String eContact;
}
