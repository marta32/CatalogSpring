package com.example.springbootcatalog.mapper;

import com.example.springbootcatalog.entity.Subject;
import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.payload.TeacherDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SubjectMapper {

    public SubjectDto mapSubjectToSubjectDto(Subject subject) {
        SubjectDto.SubjectDtoBuilder subjectDto = SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName());

        if (subject.getTeachers() != null) {
            subjectDto.teachers(subject.getTeachers().stream()
                    .map(t -> TeacherDto.builder()
                            .id(t.getId())
                            .firstName(t.getFirstName())
                            .lastName(t.getLastName())
                            .birthday(t.getBirthday())
                            .build())
                    .collect(Collectors.toSet()));
        }

        if (subject.getGrades() != null) {
            subjectDto.grades(subject.getGrades().stream()
                    .map(g -> GradeDto.builder()
                            .id(g.getId())
                            .mark(g.getMark())
                            .dateMark(g.getDateMark())
                            .studentId(g.getStudent().getId())
                            .build())
                    .collect(Collectors.toSet()));
        }

        return subjectDto.build();
    }

    public Subject mapSubjectDtoToSubject(SubjectDto subjectDto) {
        return Subject.builder()
                .name(subjectDto.getName())
                .build();
    }

}