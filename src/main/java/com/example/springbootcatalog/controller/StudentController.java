package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.StudentAverageGradeDto;
import com.example.springbootcatalog.payload.StudentDto;
import com.example.springbootcatalog.service.StudentService;
import com.example.springbootcatalog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Create a student")
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all students",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all students")
            }
    )
    @GetMapping
    public ObjectResponse<StudentDto> getAllStudents(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return studentService.getAllStudents(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(summary = "Get a student by id")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Operation(summary = "Get top n students in a subject")
    @GetMapping("/searchTopStudentsBySubject")
    public ResponseEntity<List<StudentAverageGradeDto>> searchTopStudentsBySubject(
            @RequestParam(value = "subject", defaultValue = AppConstants.DEFAULT_ID_SUBJECT, required = true) int subject,
            @RequestParam(value = "top", defaultValue = AppConstants.DEFAULT_TOP_STUDENT, required = true) int top
    ) {
        return ResponseEntity.ok(studentService.getTopStudentsBySubject(subject, top));
    }

    @Operation(summary = "Get students with learning problems in a subject")
    @GetMapping("/searchStudentsLearningProblems")
    public ResponseEntity<List<StudentAverageGradeDto>> searchStudentsLearningProblems(
            @RequestParam(value = "subject", defaultValue = AppConstants.DEFAULT_ID_SUBJECT, required = true) int subject
    ) {
        return ResponseEntity.ok(studentService.getStudentsLearningProblems(subject));
    }

    @Operation(summary = "Update a student by id")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto,
                                                    @PathVariable(name = "id") Integer id) {
        StudentDto studentResponse = studentService.updateStudent(studentDto, id);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete a student by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student entity deleted successfully.", HttpStatus.OK);
    }

}