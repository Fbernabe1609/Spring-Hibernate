package com.example.h2hibernate.dao;

import com.example.h2hibernate.model.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> findAll();
    Subject findById(int id);
    void save(Subject user);
    void deleteById(int id);
    void update(Subject subject);
}
