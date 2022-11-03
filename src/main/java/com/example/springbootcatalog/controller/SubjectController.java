package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.service.SubjectService;
import com.example.springbootcatalog.utils.AppConstants;
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

    @GetMapping
    public ObjectResponse<SubjectDto> getAllSubjects(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                     @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return subjectService.getAllSubjects(pageNo, pageSize, sortBy, sortDir);
    }


}
