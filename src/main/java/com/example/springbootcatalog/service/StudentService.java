package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.StudentDto;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);
    ObjectResponse<StudentDto> getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);
    StudentDto getStudentById(Integer id);
    StudentDto updateStudent(StudentDto studentDto, Integer id);
}