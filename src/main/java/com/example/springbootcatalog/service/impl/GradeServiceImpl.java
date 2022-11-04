package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Grade;
import com.example.springbootcatalog.mapper.GradeMapper;
import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.repository.GradeRepository;
import com.example.springbootcatalog.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeMapper mapper;

    @Override
    public GradeDto createGrade(GradeDto gradeDto) {
        Grade grade = mapper.mapGradeDtoToGrade(gradeDto);
        gradeRepository.save(grade);
        return mapper.mapGradeToGradeDto(grade);
    }
}
