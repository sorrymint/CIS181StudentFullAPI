package com.example.studentdemojpa.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateDto {

    @Email(message = "Must be valid email")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Must be exactly 10 digits")
    @JsonProperty("eContact")
    private String eContact;

}
