package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.service.TeacherService;
import com.example.springbootcatalog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootcatalog.payload.TeacherDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Create a teacher")
    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all teachers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all teachers")
            }
    )
    @GetMapping
    public ObjectResponse<TeacherDto> getAllTeachers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return teacherService.getAllTeachers(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(summary = "Get a teacher by id")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @Operation(summary = "Update a teacher by id")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@Valid @RequestBody TeacherDto teacherDto,
                                                    @PathVariable(name = "id") Integer id) {
        TeacherDto teacherResponse = teacherService.updateTeacher(teacherDto, id);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete a teacher by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable(name = "id") Integer id) {
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>("Teacher entity deleted successfully.", HttpStatus.OK);
    }

}