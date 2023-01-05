package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.mapper.TeacherMapper;
import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springbootcatalog.payload.TeacherDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper mapper;

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = mapper.mapTeacherDtoToTeacher(teacherDto);
        teacherRepository.save(teacher);
        return mapper.mapTeacherToTeacherDto(teacher);
    }

    @Override
    public ObjectResponse<TeacherDto> getAllTeachers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Teacher> teachers = teacherRepository.findAll(pageable);
        List<Teacher> listOfTeachers = teachers.getContent();
        List<TeacherDto> newContent = listOfTeachers.stream()
                .map(teacher -> mapper.mapTeacherToTeacherDto(teacher)).collect(Collectors.toList());

        return  ObjectResponse.<TeacherDto>builder()
                .content(newContent)
                .pageNo(teachers.getNumber())
                .pageSize(teachers.getSize())
                .totalElements(teachers.getTotalElements())
                .totalPages(teachers.getTotalPages())
                .last(teachers.isLast())
                .build();
    }

    @Override
    public TeacherDto getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", id));
        return mapper.mapTeacherToTeacherDto(teacher);
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto, Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", id));
        if (teacherDto.getFirstName() != null) {
            teacher.setFirstName(teacherDto.getFirstName());
        }
        if (teacherDto.getLastName() != null) {
            teacher.setLastName(teacherDto.getLastName());
        }
        if (teacherDto.getBirthday() != null) {
            teacher.setBirthday(teacherDto.getBirthday());
        }
        Teacher updateTeacher = teacherRepository.save(teacher);
        return mapper.mapTeacherToTeacherDto(updateTeacher);
    }

    @Override
    public void deleteTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", id));
        teacherRepository.delete(teacher);
    }

}