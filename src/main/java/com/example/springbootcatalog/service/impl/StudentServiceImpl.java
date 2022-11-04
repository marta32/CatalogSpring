package com.example.springbootcatalog.service.impl;

import com.example.springbootcatalog.entity.Student;
import com.example.springbootcatalog.exception.ResourceNotFoundException;
import com.example.springbootcatalog.mapper.StudentMapper;
import com.example.springbootcatalog.payload.ObjectResponse;
import com.example.springbootcatalog.payload.StudentDto;
import com.example.springbootcatalog.repository.StudentRepository;
import com.example.springbootcatalog.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private StudentMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = mapper.mapStudentDtoToStudent(studentDto);
        Student newStudent = studentRepository.save(student);
        return mapper.mapStudentToStudentDto(newStudent);
    }

    @Override
    public ObjectResponse<StudentDto> getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> students = studentRepository.findAll(pageable);
        List<Student> listOfTeachers = students.getContent();
        List<StudentDto> content = listOfTeachers.stream()
                .map(student -> mapper.mapStudentToStudentDto(student)).collect(Collectors.toList());

        ObjectResponse<StudentDto> studentResponse = new ObjectResponse<>();
        studentResponse.setContent(content);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalElements(students.getTotalElements());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setLast(students.isLast());

        return studentResponse;
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

}