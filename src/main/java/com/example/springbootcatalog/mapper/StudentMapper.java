package com.example.springbootcatalog.mapper;

import com.example.springbootcatalog.entity.Student;
import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.StudentDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {
    public StudentDto mapStudentToStudentDto(Student student) {
        var studentDto = StudentDto.builder()
                .id(student.getId())
                .birthday(student.getBirthday())
                .firstName(student.getFirstName())
                .lastName(student.getLastName());
        if (student.getGrades() != null){
            studentDto.grades(student.getGrades().stream()
                    .map(g-> GradeDto.builder()
                            .id(g.getId())
                            .dateMark(g.getDateMark())
                            .mark(g.getMark())
                            .student(g.getStudent())
                            .subject(g.getSubject())
                            .build())
                    .collect(Collectors.toSet()));
        }
        return studentDto.build();
    }

    public Student mapStudentDtoToStudent(StudentDto studentDto){
        return Student.builder()
                .birthday(studentDto.getBirthday())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .build();
    }
}