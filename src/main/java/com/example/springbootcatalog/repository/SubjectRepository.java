package com.example.springbootcatalog.repository;

import com.example.springbootcatalog.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
