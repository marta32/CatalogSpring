package com.example.springbootcatalog.controller;

import com.example.springbootcatalog.entity.Teacher;
import com.example.springbootcatalog.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable(name = "id") Integer id) {
        return teacherService.getTeacherById(id);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable(name = "id") Integer id) {
        return teacherService.updateTeacher(teacher, id);
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable(name = "id") Integer id) {
        teacherService.deleteTeacherById(id);
        return "Teacher entity deleted successfully.";
    }
}
