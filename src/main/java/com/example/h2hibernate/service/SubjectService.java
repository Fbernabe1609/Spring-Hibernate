package com.example.h2hibernate.service;

import com.example.h2hibernate.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();
    Subject findById(int id);
    void save(Subject subject);
    void deleteById(int id);
    void update(Subject subject);
}
