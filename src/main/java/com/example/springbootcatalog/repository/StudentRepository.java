package com.example.springbootcatalog.repository;

import com.example.springbootcatalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
