package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.StudentAverageGradeDto;
import com.example.springbootcatalog.payload.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    ObjectResponse<StudentDto> getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);

    StudentDto getStudentById(Integer id);

    StudentDto updateStudent(StudentDto studentDto, Integer id);

    void deleteStudentById(Integer id);

    List<StudentAverageGradeDto> getTopStudentsBySubject(Integer subject, Integer top);

    List<StudentAverageGradeDto> getStudentsLearningProblems(Integer subject);

}