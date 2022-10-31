package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        return  teacherRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher,Integer id) {
        Teacher newTeacher = teacherRepository.findById(id).orElseThrow(null);
        newTeacher.setFirstName(teacher.getFirstName());
        newTeacher.setLastName(teacher.getLastName());
        newTeacher.setBirthday(teacher.getBirthday());
        Teacher updateTeacher = teacherRepository.save(newTeacher);
        return updateTeacher;
    }

    @Override
    public void deleteTeacherById(Integer id) {
        teacherRepository.deleteById(id);
    }
}
