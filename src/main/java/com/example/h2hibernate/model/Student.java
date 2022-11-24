package com.example.h2hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String DNI;
    @Column
    private int age;
    @ManyToMany
    @JoinTable(name = "enrollments",
            joinColumns = @JoinColumn(name = "StudentID"),
            inverseJoinColumns = @JoinColumn(name = "SubjectID")
    )
    private ArrayList<Subject> subjects = new ArrayList<>();
}
