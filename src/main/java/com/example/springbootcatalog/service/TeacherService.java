package com.example.springbootcatalog.service;

import com.example.springbootcatalog.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Integer id);

    Teacher updateTeacher(Teacher teacher);

    void deleteTeacherById(Integer id);

}
