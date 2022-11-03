package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.payload.TeacherDto;
import com.example.springbootcatalog.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(subjectService.createSubject(subjectDto), HttpStatus.CREATED);
    }

    @PostMapping("/{subjectId}/teachers/{teacherId}")
    public ResponseEntity<SubjectDto> addTeacherToSubject(@PathVariable(value = "subjectId") Integer subjectId, @PathVariable(value = "teacherId") Integer teacherId) {
        return new ResponseEntity<>(subjectService.addTeacherToSubject(subjectId, teacherId), HttpStatus.CREATED);
    }


}
