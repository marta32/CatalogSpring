package com.example.springbootcatalog.payload;

import com.example.springbootcatalog.entity.Grade;
import com.example.springbootcatalog.entity.Teacher;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class SubjectDto {
    private Integer id;
    @NotEmpty(message = "Name of subject should not be empty")
    private String name;
    private Set<Teacher> teachers;
    private Set<Grade> grades;
}