package com.example.springbootcatalog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "subject_teacher", joinColumns = @JoinColumn(name = "subjectId", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "teacherId", referencedColumnName = "id"))
    @ManyToMany(targetEntity = Teacher.class, cascade = CascadeType.ALL)
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Grade> grades;

//    public void addTeacher(Teacher teacher) {
//        this.teachers.add(teacher);
//        teacher.getSubjects().add(this);
//    }
//
//    public void removeTeacher(Integer teacherId) {
//        Teacher teacher = this.teachers.stream().filter(t -> t.getId().equals(teacherId)).findFirst().orElse(null);
//        if (teacher != null) {
//            this.teachers.remove(teacher);
//            teacher.getSubjects().remove(this);
//        }
//    }

}
