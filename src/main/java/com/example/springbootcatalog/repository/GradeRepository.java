package com.example.springbootcatalog.repository;

import com.example.springbootcatalog.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer>{

    // search all grades with the same student_id;
    @Query(value = "SELECT g.id, s.name as subject, g.date_mark as dateMark, g.mark as mark FROM grades g " +
            "JOIN subjects s ON s.id = g.subject_id " +
            "WHERE g.student_id = :student " +
            "ORDER BY g.subject_id, g.date_mark", nativeQuery = true
    )
    List<Tuple> getGradesByStudent(Integer student);

}