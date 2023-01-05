package com.example.springbootcatalog.repository;

import com.example.springbootcatalog.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}