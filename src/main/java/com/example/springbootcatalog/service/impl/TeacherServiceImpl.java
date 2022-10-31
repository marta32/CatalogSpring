package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import payload.TeacherDto;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;
    private ModelMapper mapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, ModelMapper mapper) {
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = mapper.map(teacherDto, Teacher.class);
        Teacher newTeacher = teacherRepository.save(teacher);
        return mapper.map(newTeacher, TeacherDto.class);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherDto getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(null);
        return mapper.map(teacher, TeacherDto.class);

    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto, Integer id) {
        Teacher newTeacher = teacherRepository.findById(id).orElseThrow(null);
        newTeacher.setFirstName(teacherDto.getFirstName());
        newTeacher.setLastName(teacherDto.getLastName());
        newTeacher.setBirthday(teacherDto.getBirthday());
        Teacher updateTeacher = teacherRepository.save(newTeacher);
        return mapper.map(updateTeacher,TeacherDto.class);
    }

    @Override
    public void deleteTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(null);
        teacherRepository.delete(teacher);
    }
}
