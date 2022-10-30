package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
//        return teacherRepository.findAll();
        return null;
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        return null;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public void deleteTeacherById(Integer id) {

    }
}
