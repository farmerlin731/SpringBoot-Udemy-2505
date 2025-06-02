package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    //define field
    private EntityManager entityManager;

    //set up constructor
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        //execute query
        List<Employee> employees = theQuery.getResultList();

        //return result
        return employees;
    }
}
