package com.example.springbootcatalog.repository;

import com.example.springbootcatalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // search top n good students in a subject;
    @Query(value = "SELECT s.first_name as firstName, s.last_name as lastName, avg(g.mark) as average FROM grades g " +
            "JOIN students s ON s.id = g.student_id " +
            "WHERE g.subject_id = :subject " +
            "GROUP BY g.student_id, g.subject_id, s.first_name, s.last_name " +
            "ORDER BY avg(g.mark) desc " +
            "limit :top", nativeQuery = true
    )
    List<Tuple> searchTopStudentsBySubject(Integer subject, Integer top);


    // search students with an averange below 5 in a subject;
    @Query(value = "SELECT s.first_name as firstName, s.last_name as lastName, avg(g.mark) as average FROM grades g " +
            "JOIN students s ON s.id = g.student_id " +
            "WHERE g.subject_id = :subject " +
            "GROUP BY g.student_id, g.subject_id, s.first_name, s.last_name " +
            "HAVING AVG(g.mark)<5 " +
            "ORDER BY s.first_name, s.last_name", nativeQuery = true
    )
    List<Tuple> searchStudentsLearningProblems(Integer subject);



}
