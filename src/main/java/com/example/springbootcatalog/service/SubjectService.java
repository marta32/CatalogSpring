package com.example.springbootcatalog.service;

import com.example.springbootcatalog.payload.SubjectDto;

public interface SubjectService {

    SubjectDto createSubject(SubjectDto subjectDto);

    SubjectDto addTeacherToSubject(Integer subjectId, Integer teacherId);
}