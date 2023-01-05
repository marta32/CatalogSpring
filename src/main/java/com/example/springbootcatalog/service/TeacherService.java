package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.TeacherDto;

public interface TeacherService {

    TeacherDto createTeacher(TeacherDto teacherDto);

    ObjectResponse<TeacherDto> getAllTeachers(int pageNo, int pageSize, String sortBy, String sortDir);

    TeacherDto getTeacherById(Integer id);

    TeacherDto updateTeacher(TeacherDto teacherDto, Integer id);

    void deleteTeacherById(Integer id);

}