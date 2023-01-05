package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.StudentGradesDto;
import com.example.springbootcatalog.payload.UpdateGradeDto;

import java.util.List;

public interface GradeService {

    GradeDto createGrade(GradeDto gradeDto);

    GradeDto updateGrade(UpdateGradeDto gradeDto, Integer id);

    void deleteGradeById(Integer id);

    List<StudentGradesDto> getGradesByStudent(Integer student);

}