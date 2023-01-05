package com.example.springbootcatalog.mapper;

import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.payload.TeacherDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    public TeacherDto mapTeacherToTeacherDto(Teacher teacher) {
        TeacherDto.TeacherDtoBuilder teacherDto = TeacherDto.builder()
                .id(teacher.getId())
                .birthday(teacher.getBirthday())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName());

        if (teacher.getSubjects() != null) {
            teacherDto.subjectsSet(teacher.getSubjects().stream()
                    .map(s -> SubjectDto.builder()
                            .id(s.getId())
                            .name(s.getName())
                            .build())
                    .collect(Collectors.toSet()));
        }

        return teacherDto.build();
    }

    public Teacher mapTeacherDtoToTeacher(TeacherDto teacherDto) {
        return Teacher.builder()
                .birthday(teacherDto.getBirthday())
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .build();
    }

}