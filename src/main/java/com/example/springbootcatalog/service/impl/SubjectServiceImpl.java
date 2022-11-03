package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Subject;
import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.repository.SubjectRepository;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.SubjectService;
//import com.example.springbootcatalog.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    private TeacherRepository teacherRepository;

    private ModelMapper mapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, TeacherRepository teacherRepository,ModelMapper mapper) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = mapper.map(subjectDto,Subject.class);
//        Subject subject = new Mapper().mapSubjectDtoToSubject(subjectDto);
        Subject newSubject = subjectRepository.save(subject);
        return mapper.map(newSubject, SubjectDto.class);
//        return new Mapper().mapSubjectToSubjectDto(subject);
    }

    @Override
    public SubjectDto addTeacherToSubject(Integer subjectId, Integer teacherId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject", "subjectId", subjectId));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher", "teacherId", teacherId));
        subject.getTeachers().add(teacher);
        subjectRepository.save(subject);
        return mapper.map(subject, SubjectDto.class);
//        return new Mapper().mapSubjectToSubjectDto(subject);
    }
}