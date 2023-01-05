package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Student;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.mapper.StudentMapper;
import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.StudentAverageGradeDto;
import com.example.springbootcatalog.payload.StudentDto;
import com.example.springbootcatalog.repository.StudentRepository;
import com.example.springbootcatalog.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper mapper;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = mapper.mapStudentDtoToStudent(studentDto);
        Student newStudent = studentRepository.save(student);
        return mapper.mapStudentToStudentDto(newStudent);
    }

    @Override
    public ObjectResponse<StudentDto> getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> students = studentRepository.findAll(pageable);
        List<Student> listOfTeachers = students.getContent();
        List<StudentDto> newContent = listOfTeachers.stream()
                .map(student -> mapper.mapStudentToStudentDto(student))
                .collect(Collectors.toList());

        return ObjectResponse.<StudentDto>builder()
                .content(newContent)
                .pageNo(students.getNumber())
                .pageSize(students.getSize())
                .totalElements(students.getTotalElements())
                .totalPages(students.getTotalPages())
                .last(students.isLast())
                .build();
    }

    @Override
    public StudentDto getStudentById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return mapper.mapStudentToStudentDto(student);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        if (studentDto.getFirstName() != null) {
            student.setFirstName(studentDto.getFirstName());
        }

        if (studentDto.getLastName() != null) {
            student.setLastName(studentDto.getLastName());
        }

        if (studentDto.getBirthday() != null) {
            student.setBirthday(studentDto.getBirthday());
        }

        Student updateStudent = studentRepository.save(student);
        return mapper.mapStudentToStudentDto(updateStudent);
    }

    @Override
    public void deleteStudentById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        studentRepository.delete(student);
    }

    @Override
    public List<StudentAverageGradeDto> getTopStudentsBySubject(Integer subject, Integer top) {
        return studentRepository.getTopStudentsBySubject(subject, top).stream()
                .map(tuple -> mapper.mapObjectToStudentAvgGrade(tuple))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentAverageGradeDto> getStudentsLearningProblems(Integer subject) {
        return studentRepository.getStudentsLearningProblems(subject).stream()
                .map(tuple -> mapper.mapObjectToStudentAvgGrade(tuple))
                .collect(Collectors.toList());
    }

}