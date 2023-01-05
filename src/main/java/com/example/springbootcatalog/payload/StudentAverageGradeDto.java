package com.example.springbootcatalog.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAverageGradeDto {
    private String firstName;
    private String lastName;
    private Double average;

}