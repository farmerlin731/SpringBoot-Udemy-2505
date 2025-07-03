package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    //Create
    void save(Instructor theInstructor);

    //Read
    Instructor findInstructorById(int id);

    //Delete
    void deleteInstructorById(int theId);

    //Read Detail
    InstructorDetail findInstructorDetailById(int theId);
}
