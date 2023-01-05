package com.example.springbootcatalog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "subject_teacher", joinColumns = @JoinColumn(name = "subjectId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacherId", referencedColumnName = "id"))
    private Set<Teacher> teachers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Grade> grades;

}