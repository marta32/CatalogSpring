package com.example.springbootcatalog.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GradeDto {
    private Integer id;
    @NotNull
    private Integer studentId;
    @NotNull
    private Integer subjectId;
    @NotNull
    private Integer mark;
    private LocalDate dateMark;
}
