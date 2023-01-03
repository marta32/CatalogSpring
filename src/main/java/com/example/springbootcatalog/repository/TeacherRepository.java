package com.example.springbootcatalog.repository;

import com.example.springbootcatalog.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Tuple;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

//     search teachers which teach at least two subjects
//    List<Tuple> searchTeacher();

}