package com.example.springbootcatalog.payload;

import com.example.springbootcatalog.entity.Grade;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    private Integer id;
    @NotEmpty(message = "First name should not be empty")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty")
    private String lastName;
    //@NotEmpty(message = "Date of birth should not be empty")
    private LocalDate birthday;
    private Set<GradeDto> grades;
}