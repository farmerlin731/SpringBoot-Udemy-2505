package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    //Create
    void save(Instructor theInstructor);

    //Read
    Instructor findInstructorById(int id);

    //Delete
    void deleteInstructorById(int theId);

    //Read Detail
    InstructorDetail findInstructorDetailById(int theId);

    //Delete Detail
    void deleteInstructorDetailById(int theId);

    //Read Courses
    List<Course> findCoursesByInstructorId(int theId);

    //Read Instructor and courses at the same time
    Instructor findInstructorByIdJoinFetch(int theId);

}
