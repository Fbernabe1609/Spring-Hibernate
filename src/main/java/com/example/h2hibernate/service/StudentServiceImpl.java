package com.example.h2hibernate.service;

import com.example.h2hibernate.dao.StudentDAOImpl;
import com.example.h2hibernate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAOImpl studentDAO;

    @Override
    public List<Student> findAll() {
        List<Student> listUsers = studentDAO.findAll();
        return listUsers;
    }

    @Override
    public Student findById(int id) {
        Student user = studentDAO.findById(id);
        return user;
    }

    @Override
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }

    @Override
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }
}
