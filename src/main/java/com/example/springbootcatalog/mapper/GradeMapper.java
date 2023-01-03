package com.example.springbootcatalog.mapper;

import com.example.springbootcatalog.entity.Grade;
import com.example.springbootcatalog.entity.Student;
import com.example.springbootcatalog.entity.Subject;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.StudentAverageGradeDto;
import com.example.springbootcatalog.payload.StudentGradesDto;
import com.example.springbootcatalog.repository.StudentRepository;
import com.example.springbootcatalog.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

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
        Optional<Subject> sub = subjectRepository.findById(gradeDto.getSubjectId());
        Optional<Student> stud = studentRepository.findById(gradeDto.getStudentId());
        return Grade.builder()
                .mark(gradeDto.getMark())
                .dateMark(gradeDto.getDateMark())
                .student(stud.orElseThrow(()->new ResourceNotFoundException("Student", "id",gradeDto.getStudentId())))
                .subject(sub.orElseThrow(()->new ResourceNotFoundException("Subject", "id",gradeDto.getSubjectId())))
                .build();
    }

    public StudentGradesDto mapObjectToStudentGradesDto(Tuple tuple){
        return StudentGradesDto.builder()
                .id(tuple.get(0, Integer.class))
                .name(tuple.get(1,String.class))
                .dateMark(tuple.get(2, Date.class))
                .mark(tuple.get(3, Integer.class))
                .build();
    }
}
