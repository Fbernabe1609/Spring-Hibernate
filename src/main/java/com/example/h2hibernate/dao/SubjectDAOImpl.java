package com.example.h2hibernate.dao;

import com.example.h2hibernate.model.Subject;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class SubjectDAOImpl implements SubjectDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Subject> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Subject> theQuery = currentSession.createQuery("from Subject ", Subject.class);
        return theQuery.getResultList();
    }

    @Override
    public Subject findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Subject.class, id);
    }

    @Transactional
    @Override
    public void save(Subject subject) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(subject);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Subject> theQuery = currentSession.createQuery("delete from Subject where id=:id");
        theQuery.setParameter("id", id);
        theQuery.executeUpdate();
    }

    @Transactional
    @Override
    public void update(Subject subject) {
        Session curreSession = entityManager.unwrap(Session.class);
        curreSession.merge(subject);
    }
}
