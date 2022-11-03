package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Student;
import com.example.springbootcatalog.entity.Subject;
import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.mapper.SubjectMapper;
import com.example.springbootcatalog.mapper.TeacherMapper;
import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.StudentDto;
import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.payload.TeacherDto;
import com.example.springbootcatalog.repository.SubjectRepository;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.SubjectService;
//import com.example.springbootcatalog.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    private TeacherRepository teacherRepository;

    private SubjectMapper mapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, TeacherRepository teacherRepository, SubjectMapper mapper) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = mapper.mapSubjectDtoToSubject(subjectDto);
        Subject newSubject = subjectRepository.save(subject);
        return mapper.mapSubjectToSubjectDto(newSubject);
    }

    @Override
    public SubjectDto addTeacherToSubject(Integer subjectId, Integer teacherId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject", "subjectId", subjectId));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher", "teacherId", teacherId));
        subject.getTeachers().add(teacher);
        subjectRepository.save(subject);
        return mapper.mapSubjectToSubjectDto(subject);
    }

    @Override
    public ObjectResponse<SubjectDto> getAllSubjects(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Subject> subjects = subjectRepository.findAll(pageable);
        List<Subject> listOfSubjects = subjects.getContent();
        List<SubjectDto> content = listOfSubjects.stream()
                .map(subject -> mapper.mapSubjectToSubjectDto(subject)).collect(Collectors.toList());

        ObjectResponse<SubjectDto> subjectResponse = new ObjectResponse<SubjectDto>();
        subjectResponse.setContent(content);
        subjectResponse.setPageNo(subjects.getNumber());
        subjectResponse.setPageSize(subjects.getSize());
        subjectResponse.setTotalElements(subjects.getTotalElements());
        subjectResponse.setTotalPages(subjects.getTotalPages());
        subjectResponse.setLast(subjects.isLast());
        return subjectResponse;
    }

    public SubjectDto getSubjectById(Integer id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        return mapper.mapSubjectToSubjectDto(subject);
    }
}