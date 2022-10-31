package com.example.springbootcatalog.payload;

import com.example.springbootcatalog.entity.Subject;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Data
public class TeacherDto {
    private Integer id;
    @NotEmpty(message = "First name should not be empty")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty")
    private String lastName;
    private LocalDate birthday;
    private Set<Subject> subjects;
}