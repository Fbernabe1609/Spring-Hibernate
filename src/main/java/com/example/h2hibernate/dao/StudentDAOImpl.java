package com.example.h2hibernate.dao;

import com.example.h2hibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Student> theQuery = currentSession.createQuery("from Student ", Student.class);

        List<Student> students = theQuery.getResultList();
        return students;
    }

    @Override
    public Student findById(int id) {
        Session currentSesion = entityManager.unwrap(Session.class);
        Student user = currentSesion.get(Student.class, id);

        return user;
    }

    @Transactional
    @Override
    public void save(Student student) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(student);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Student> theQuery = currentSession.createQuery("delete from Student where id=:id");
        theQuery.setParameter("id", id);
        theQuery.executeUpdate();
    }

    @Transactional
    @Override
    public void update(Student student) {
        Session curreSession = entityManager.unwrap(Session.class);
        curreSession.merge(student);
    }
}
