package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.StudentAverageGradeDto;
import com.example.springbootcatalog.payload.StudentDto;
import com.example.springbootcatalog.service.StudentService;
import com.example.springbootcatalog.utils.AppConstants;
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

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ObjectResponse<StudentDto> getAllStudents(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return studentService.getAllStudents(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/searchTopStudentsBySubject")
    public ResponseEntity<List<StudentAverageGradeDto>> searchTopStudentsBySubject(
            @RequestParam(value = "subject", defaultValue = AppConstants.DEFAULT_ID_SUBJECT, required = true) int subject,
            @RequestParam(value = "top", defaultValue = AppConstants.DEFAULT_TOP_STUDENT, required = true) int top
    ) {
        return ResponseEntity.ok(studentService.getTopStudentsBySubject(subject, top));
    }

    @GetMapping("/searchStudentsLearningProblems")
    public ResponseEntity<List<StudentAverageGradeDto>> searchStudentsLearningProblems(
            @RequestParam(value = "subject", defaultValue = AppConstants.DEFAULT_ID_SUBJECT, required = true) int subject
    ) {
        return ResponseEntity.ok(studentService.getStudentsLearningProblems(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto,
                                                    @PathVariable(name = "id") Integer id) {
        StudentDto studentResponse = studentService.updateStudent(studentDto, id);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student entity deleted successfully.", HttpStatus.OK);
    }

}