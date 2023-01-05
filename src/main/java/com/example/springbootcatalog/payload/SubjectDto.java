package com.example.springbootcatalog.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto {
    private Integer id;
    @NotEmpty(message = "Name of subject should not be empty")
    private String name;
    private Set<TeacherDto> teachers;
    private Set<GradeDto> grades;

}