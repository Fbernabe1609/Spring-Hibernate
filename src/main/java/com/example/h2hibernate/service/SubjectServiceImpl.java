package com.example.h2hibernate.service;

import com.example.h2hibernate.dao.SubjectDAOImpl;
import com.example.h2hibernate.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDAOImpl subjectDAO;

    @Override
    public List<Subject> findAll() {
        return subjectDAO.findAll();
    }

    @Override
    public Subject findById(int id) {
        return subjectDAO.findById(id);
    }

    @Override
    public void save(Subject subject) {
        subjectDAO.save(subject);
    }

    @Override
    public void deleteById(int id) {
        subjectDAO.deleteById(id);
    }

    @Override
    public void update(Subject subject) {
        subjectDAO.update(subject);
    }
}
