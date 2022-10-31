package com.example.springbootcatalog.payload;

import com.example.springbootcatalog.entity.Student;
import com.example.springbootcatalog.entity.Subject;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class GradeDto {
    private Integer id;
    @NotEmpty(message = "Student is missing")
    private Student student;
    @NotEmpty(message = "Subject is missing")
    private Subject subject;
    @NotEmpty(message = "Mark is missing")
    private Integer mark;
    private LocalDate dateMark;
}
