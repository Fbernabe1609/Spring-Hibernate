package com.example.h2hibernate.dao;

import com.example.h2hibernate.model.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> findAll();
    Student findById(int id);
    void save(Student user);
    void deleteById(int id);
    void update(Student student);
}
