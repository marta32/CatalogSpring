package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Subject;
import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.mapper.SubjectMapper;
import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.repository.SubjectRepository;
import com.example.springbootcatalog.repository.TeacherRepository;
import com.example.springbootcatalog.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectMapper mapper;

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = mapper.mapSubjectDtoToSubject(subjectDto);
        Subject newSubject = subjectRepository.save(subject);
        return mapper.mapSubjectToSubjectDto(newSubject);
    }

    @Override
    public SubjectDto addTeacherToSubject(Integer subjectId, Integer teacherId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(()
                -> new ResourceNotFoundException("Subject", "subjectId", subjectId));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()
                -> new ResourceNotFoundException("Teacher", "teacherId", teacherId));
        subject.getTeachers().add(teacher);
        subjectRepository.save(subject);
        return mapper.mapSubjectToSubjectDto(subject);
    }

    @Override
    public ObjectResponse<SubjectDto> getAllSubjects(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Subject> subjects = subjectRepository.findAll(pageable);
        List<Subject> listOfSubjects = subjects.getContent();
        List<SubjectDto> newContent = listOfSubjects.stream()
                .map(subject -> mapper.mapSubjectToSubjectDto(subject)).collect(Collectors.toList());

        return ObjectResponse.<SubjectDto>builder()
                .content(newContent)
                .pageNo(subjects.getNumber())
                .pageSize(subjects.getSize())
                .totalElements(subjects.getTotalElements())
                .totalPages(subjects.getTotalPages())
                .last(subjects.isLast())
                .build();
    }

    public SubjectDto getSubjectById(Integer id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        return mapper.mapSubjectToSubjectDto(subject);
    }

    @Override
    public SubjectDto updateSubject(SubjectDto subjectDto, Integer id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        subject.setName(subjectDto.getName());
        Subject updateSubject = subjectRepository.save(subject);
        return mapper.mapSubjectToSubjectDto(updateSubject);
    }

    @Override
    public void deleteSubjectById(Integer id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        subjectRepository.delete(subject);
    }

}