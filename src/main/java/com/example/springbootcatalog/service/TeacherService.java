package com.example.springbootcatalog.service;

import com.example.springbootcatalog.entity.Teacher;
import payload.TeacherDto;

import java.util.List;

public interface TeacherService {

    TeacherDto createTeacher(TeacherDto teacherDto);

    List<Teacher> getAllTeachers();

    TeacherDto getTeacherById(Integer id);

    TeacherDto updateTeacher(TeacherDto teacherDto, Integer id);

    void deleteTeacherById(Integer id);

}
