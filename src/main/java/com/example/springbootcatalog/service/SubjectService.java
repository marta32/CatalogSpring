package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.SubjectDto;

public interface SubjectService {

    SubjectDto createSubject(SubjectDto subjectDto);

    SubjectDto addTeacherToSubject(Integer subjectId, Integer teacherId);

    ObjectResponse<SubjectDto> getAllSubjects(int pageNo, int pageSize, String sortBy, String sortDir);

    SubjectDto getSubjectById(Integer id);

    SubjectDto updateSubject(SubjectDto subjectDto, Integer id);

    void deleteSubjectById(Integer id);

}