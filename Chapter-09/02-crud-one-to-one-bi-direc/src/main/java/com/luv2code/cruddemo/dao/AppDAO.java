package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;

public interface AppDAO {
    //Create
    void save(Instructor theInstructor);

    //Read
    Instructor findInstructorById(int id);

    //Delete
    void deleteInstructorById(int theId);
}
