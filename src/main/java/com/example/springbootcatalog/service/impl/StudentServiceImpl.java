package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Student;
import com.example.springbootcatalog.payload.StudentDto;
import com.example.springbootcatalog.repository.StudentRepository;
import com.example.springbootcatalog.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = mapper.map(studentDto, Student.class);
        Student newStudent = studentRepository.save(student);
        return mapper.map(newStudent,StudentDto.class);
    }



}