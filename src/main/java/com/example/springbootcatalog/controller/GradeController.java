package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.StudentGradesDto;
import com.example.springbootcatalog.payload.UpdateGradeDto;
import com.example.springbootcatalog.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @Operation(summary = "Create a grade for a student")
    @PostMapping
    public ResponseEntity<GradeDto> createGrade(@Valid @RequestBody GradeDto gradeDto) {
        return new ResponseEntity<>(gradeService.createGrade(gradeDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get a grade by id")
    @GetMapping("/{id}")
    public ResponseEntity<List<StudentGradesDto>> searchAllGradesForAStudent(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(gradeService.getGradesByStudent(id));
    }

    @Operation(summary = "Update a grade by id")
    @PutMapping("/{id}")
    public ResponseEntity<GradeDto> updateGrade(@Valid @RequestBody UpdateGradeDto updateGradeDto,
                                                @PathVariable(name = "id") Integer id) {
        GradeDto gradeResponse = gradeService.updateGrade(updateGradeDto, id);
        return new ResponseEntity<>(gradeResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete a grade by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrade(@PathVariable(name = "id") Integer id) {
        gradeService.deleteGradeById(id);
        return new ResponseEntity<>("Grade entity deleted successfully", HttpStatus.OK);
    }

}