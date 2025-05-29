package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    //Define field for entity manager.
    private EntityManager entityManager;

    //Inject entity manager using constructor injection.
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student findById(Integer Id) {
        return entityManager.find(Student.class, Id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student order by lastName", Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student WHERE lastName=:theData", Student.class);

        //Set parameter.
        theQuery.setParameter("theData", theLastName);

        //return result
        return theQuery.getResultList();
    }


    //Implement save method.
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student tmpStu = entityManager.find(Student.class, id);
        entityManager.remove(tmpStu);
    }
}
