package com.example.h2hibernate.service;

import com.example.h2hibernate.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int id);
    void save(Student student);
    void deleteById(int id);
    void update(Student student);
}
