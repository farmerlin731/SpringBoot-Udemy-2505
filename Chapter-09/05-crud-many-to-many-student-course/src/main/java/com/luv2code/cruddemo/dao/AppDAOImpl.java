package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    //Updated for courses field.
    public void deleteInstructorById(int theId) {
        //retrieve the data
        Instructor tmpInstructor = entityManager.find(Instructor.class, theId);

        //Get the course.
        List<Course> courses = tmpInstructor.getCourses();

        //Break the association.
        for (Course tmpCourse : courses) {
            tmpCourse.setInstructor(null);
        }

        //delete instructor
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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );

        query.setParameter("data", theId);

        //execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "where i.id = :data", Instructor.class
        );
        query.setParameter("data", theId);

        //execute query
        Instructor tmpInstructor = query.getSingleResult();

        return tmpInstructor;
    }

    @Override
    @Transactional
    public void update(Instructor tmpInstructor) {
        entityManager.merge(tmpInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void update(Course tmpCourse) {
        entityManager.merge(tmpCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //find
        Course tmpCourse = entityManager.find(Course.class, theId);
        //delete
        entityManager.remove(tmpCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsById(int theId) {
        //Create Query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "join fetch c.reviews "
                        + "where c.id = :data", Course.class
        );

        query.setParameter("data", theId);

        //Execute
        Course tmpCourse = query.getSingleResult();
        return tmpCourse;
    }

    @Override
    public Course findCourseAndStudentsById(int theId) {
        //Create Query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "join fetch c.students "
                        + "where c.id = :data", Course.class
        );

        query.setParameter("data", theId);

        //Execute
        Course tmpCourse = query.getSingleResult();
        return tmpCourse;
    }

    public Student findStudentAndCourseByStdId(int theId) {
        //Create Query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "join fetch s.courses "
                        + "where s.id = :data", Student.class
        );

        query.setParameter("data", theId);

        //Execute
        Student tmpStu = query.getSingleResult();
        return tmpStu;
    }
}
