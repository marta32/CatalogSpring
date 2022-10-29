package com.example.springbootcatalog.repository;

import com.example.springbootcatalog.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer>{
}
