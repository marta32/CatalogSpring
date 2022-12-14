package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Grade;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.mapper.GradeMapper;
import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.StudentGradesDto;
import com.example.springbootcatalog.payload.UpdateGradeDto;
import com.example.springbootcatalog.repository.GradeRepository;
import com.example.springbootcatalog.repository.StudentRepository;
import com.example.springbootcatalog.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeMapper mapper;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public GradeDto createGrade(GradeDto gradeDto) {
        Grade grade = mapper.mapGradeDtoToGrade(gradeDto);
        gradeRepository.save(grade);
        return mapper.mapGradeToGradeDto(grade);
    }

    @Override
    public GradeDto updateGrade(UpdateGradeDto updateGradeDto, Integer id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade", "id", id));

        if (updateGradeDto.getMark() != null) {
            grade.setMark(updateGradeDto.getMark());
        }

        if (updateGradeDto.getDateMark() != null) {
            grade.setDateMark(updateGradeDto.getDateMark());
        }

        Grade updateGrade = gradeRepository.save(grade);
        return mapper.mapGradeToGradeDto(updateGrade);
    }

    @Override
    public void deleteGradeById(Integer id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public List<StudentGradesDto> getGradesByStudent(Integer student) {
        return gradeRepository.getGradesByStudent(student).stream()
                .map(tuple -> mapper.mapObjectToStudentGradesDto(tuple))
                .collect(Collectors.toList());
    }

}