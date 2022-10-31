package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.TeacherDto;
import com.example.springbootcatalog.payload.TeacherResponse;

public interface TeacherService {

    TeacherDto createTeacher(TeacherDto teacherDto);

    TeacherResponse getAllTeachers(int pageNo, int pageSize, String sortBy,String sortDir);

    TeacherDto getTeacherById(Integer id);

    TeacherDto updateTeacher(TeacherDto teacherDto, Integer id);

    void deleteTeacherById(Integer id);
}
