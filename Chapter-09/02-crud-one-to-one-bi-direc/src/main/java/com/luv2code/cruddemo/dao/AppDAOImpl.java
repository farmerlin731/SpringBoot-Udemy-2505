package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {

    //define field for entity manager
    private EntityManager entityManager;

    //inject manager using constructor
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the data
        Instructor tmpInstructor = entityManager.find(Instructor.class, theId);

        //delete data
        entityManager.remove(tmpInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //retrieve
        InstructorDetail tmpInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //Modify the deleting policy.
        //Break the link.
        tmpInstructorDetail.getInstructor().setInstructorDetail(null);

        //delete
        entityManager.remove(tmpInstructorDetail);
    }


}
