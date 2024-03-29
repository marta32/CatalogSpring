package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.SubjectDto;
import com.example.springbootcatalog.service.SubjectService;
import com.example.springbootcatalog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a subject")
    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(subjectService.createSubject(subjectDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Create a connection between a teacher and a subject")
    @PostMapping("/{subjectId}/teachers/{teacherId}")
    public ResponseEntity<SubjectDto> addTeacherToSubject(@PathVariable(value = "subjectId") Integer subjectId,
                                                          @PathVariable(value = "teacherId") Integer teacherId) {
        return new ResponseEntity<>(subjectService.addTeacherToSubject(subjectId, teacherId), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all subjects",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all subjects")
            }
    )
    @GetMapping
    public ObjectResponse<SubjectDto> getAllSubjects(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return subjectService.getAllSubjects(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(summary = "Get a subject by id")
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @Operation(summary = "Update a subject by id")
    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@Valid @RequestBody SubjectDto subjectDto,
                                                    @PathVariable(name = "id") Integer id) {
        SubjectDto subjectResponse = subjectService.updateSubject(subjectDto, id);
        return new ResponseEntity<>(subjectResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete a subject by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable(name = "id") Integer id) {
        subjectService.deleteSubjectById(id);
        return new ResponseEntity<>("Subject entity deleted successfully.", HttpStatus.OK);
    }

}