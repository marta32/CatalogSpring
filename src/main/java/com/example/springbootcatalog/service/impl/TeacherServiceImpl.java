package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.TeacherService;
import org.modelmapper.ModelMapper;
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
    public ObjectResponse<TeacherDto> getAllTeachers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Teacher> teachers = teacherRepository.findAll(pageable);
        List<Teacher> listOfTeachers = teachers.getContent();
        List<TeacherDto> content = listOfTeachers.stream()
                .map(teacher -> mapper.map(teacher,TeacherDto.class)).collect(Collectors.toList());

        ObjectResponse<TeacherDto> teacherResponse = new ObjectResponse<TeacherDto>();
        teacherResponse.setContent(content);
        teacherResponse.setPageNo(teachers.getNumber());
        teacherResponse.setPageSize(teachers.getSize());
        teacherResponse.setTotalElements(teachers.getTotalElements());
        teacherResponse.setTotalPages(teachers.getTotalPages());
        teacherResponse.setLast(teachers.isLast());

        return teacherResponse;
    }

    @Override
    public TeacherDto getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher","id",id));
        return mapper.map(teacher, TeacherDto.class);

    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto, Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher","id",id));
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setBirthday(teacherDto.getBirthday());
        Teacher updateTeacher = teacherRepository.save(teacher);
        return mapper.map(updateTeacher,TeacherDto.class);
    }

    @Override
    public void deleteTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher","id",id));
        teacherRepository.delete(teacher);
    }
}
