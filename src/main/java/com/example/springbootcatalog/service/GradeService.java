package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.UpdateGradeDto;

public interface GradeService {

    GradeDto createGrade(GradeDto gradeDto);

    GradeDto updateGrade(UpdateGradeDto gradeDto, Integer id);
}
