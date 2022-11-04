package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.payload.GradeDto;
import com.example.springbootcatalog.payload.UpdateGradeDto;
import com.example.springbootcatalog.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<GradeDto> createGrade(@Valid @RequestBody GradeDto gradeDto) {
        return new ResponseEntity<>(gradeService.createGrade(gradeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDto> updateGrade(@Valid @RequestBody UpdateGradeDto updateGradeDto,
                                                @PathVariable(name = "id") Integer id) {
        GradeDto gradeResponse = gradeService.updateGrade(updateGradeDto, id);
        return new ResponseEntity<>(gradeResponse, HttpStatus.OK);
    }

}