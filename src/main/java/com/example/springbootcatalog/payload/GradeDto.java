package com.example.springbootcatalog.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GradeDto {
    private Integer id;
    @NotEmpty(message = "Student is missing")
    private Integer studentId;
    @NotEmpty(message = "Subject is missing")
    private Integer subjectId;
    @NotEmpty(message = "Mark is missing")
    private Integer mark;
    private LocalDate dateMark;
}
