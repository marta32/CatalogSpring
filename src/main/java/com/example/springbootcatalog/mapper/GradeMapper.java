package com.example.springbootcatalog.mapper;

import com.example.springbootcatalog.entity.Grade;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.repository.StudentRepository;
import com.example.springbootcatalog.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;

    public GradeDto mapGradeToGradeDto(Grade grade) {
        var gradeDto = GradeDto.builder()
                .id(grade.getId())
                .mark(grade.getMark())
                .dateMark(grade.getDateMark())
                .subjectId(grade.getSubject().getId())
                .studentId(grade.getStudent().getId())
                .build();
        return gradeDto;
    }

    public Grade mapGradeDtoToGrade(GradeDto gradeDto) {
        return Grade.builder()
                .mark(gradeDto.getMark())
                .dateMark(gradeDto.getDateMark())
                .student(studentRepository.findById(gradeDto.getStudentId()).orElseThrow(()->new ResourceNotFoundException("Student", "id",gradeDto.getStudentId())))
                .subject(subjectRepository.findById(gradeDto.getSubjectId()).orElseThrow(()->new ResourceNotFoundException("Subject", "id",gradeDto.getSubjectId())))
                .build();
    }

}
