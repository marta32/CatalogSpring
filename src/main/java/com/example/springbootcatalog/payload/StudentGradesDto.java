package com.example.springbootcatalog.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGradesDto {
    private Integer id;
    private String name;
    private Date dateMark;
    private Integer mark;
}
