package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {

        };
    }

    private void createInstructor(AppDAO appDAO) {
        //Create instructor
        Instructor tmpInstructor = new Instructor("Farmer", "Lin", "hahaha@gmail.com");
        Instructor tmpInstructor2 = new Instructor("George", "Chen", "hahaha@gmail.com");

        //Create detail
        InstructorDetail tmpInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "skiing!!");
        InstructorDetail tmpInstructorDetail2 = new InstructorDetail("http://www.luv2code.com/youtube", "dancing~~");

        //Link
        tmpInstructor.setInstructorDetail(tmpInstructorDetail);
        tmpInstructor2.setInstructorDetail(tmpInstructorDetail2);

        //save
        //note: this will save the detail object
        //because of the cascade type
        System.out.println("Saving... ");
        appDAO.save(tmpInstructor);
        appDAO.save(tmpInstructor2);
        System.out.println("Finished Saving!");
    }

    private void createInstructorWithCourse(AppDAO appDAO) {
        //Create instructor
        Instructor tmpInstructor = new Instructor("Mary", "Lin", "hahaha@gmail.com");
        Instructor tmpInstructor2 = new Instructor("Susan", "Chen", "hahaha@gmail.com");

        //Create detail & Link
        InstructorDetail tmpInstructorDetail = new InstructorDetail("http://www.youtube.com", "skiing!!");
        InstructorDetail tmpInstructorDetail2 = new InstructorDetail("http://www.google.com", "dancing~~");
        tmpInstructor.setInstructorDetail(tmpInstructorDetail);
        tmpInstructor2.setInstructorDetail(tmpInstructorDetail2);

        //Create Course & Link
        Course tmpCourse1 = new Course("Air Guitar - Beginner");
        Course tmpCourse2 = new Course("Dance - Advanced");
        Course tmpCourse3 = new Course("Coding - Java");
        Course tmpCourse4 = new Course("Coding - GoLang");
        tmpInstructor.add(tmpCourse1);
        tmpInstructor.add(tmpCourse2);
        tmpInstructor2.add(tmpCourse3);
        tmpInstructor2.add(tmpCourse4);

        //save
        //note: this will save the detail & course
        //because of the cascade type
        System.out.println("Saving... " + tmpInstructor);
        System.out.println("Course:" + tmpInstructor.getCourses());
        System.out.println("Saving... " + tmpInstructor2);
        System.out.println("Course:" + tmpInstructor2.getCourses());
        appDAO.save(tmpInstructor);
        appDAO.save(tmpInstructor2);
        System.out.println("Finished Saving!");


    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Reading...");
        Instructor tmpInstructor = appDAO.findInstructorById(theId);
        System.out.println("Finished Reading! the found instructor is " + tmpInstructor.getLastName() + " " + tmpInstructor.getFirstName());
        System.out.println("And the detail is " + tmpInstructor.getInstructorDetail().toString());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting... id:" + theId);
        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Reading...");
        InstructorDetail tmpInstructorDetail = appDAO.findInstructorDetailById(theId);
        System.out.println("Finished Reading! the found detail is " + tmpInstructorDetail.toString());
        System.out.println("And the corresponding instructor is " + tmpInstructorDetail.getInstructor());
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Deleting detail... id:" + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorWithCourse(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Reading...");

        //lazy -> only find instructor
        Instructor tmpInstructor = appDAO.findInstructorById(theId);

        System.out.println("Finished Reading! the found instructor is " + tmpInstructor.getLastName() + " " + tmpInstructor.getFirstName());
        System.out.println("And the courses are : " + tmpInstructor.getCourses());

    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        // Find Instructor
        int theId = 1;
        System.out.println("Reading Instructor...");
        //lazy -> only find instructor
        Instructor tmpInstructor = appDAO.findInstructorById(theId);
        System.out.println("The found instructor is " + tmpInstructor.getLastName() + " " + tmpInstructor.getFirstName());

        // Find Courses
        System.out.println("Reading Courses...");
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        tmpInstructor.setCourses(courses);
        System.out.println("The courses are : " + tmpInstructor.getCourses());

        System.out.println("Finish Reading...!");
    }

    private void findInstructorWithCourseJoinFetch(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Reading... Instructor(+Join Fetch)");

        //lazy + Join Fetch
        Instructor tmpInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("Finished Reading! the found instructor is " + tmpInstructor.getLastName() + " " + tmpInstructor.getFirstName());
        System.out.println("And the courses are : " + tmpInstructor.getCourses());

    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Reading..");
        Instructor tmpInstructor = appDAO.findInstructorById(theId);
        System.out.println("Finished! the instructor is" + tmpInstructor);

        System.out.println("Updating...");
        tmpInstructor.setLastName("Test");
        appDAO.update(tmpInstructor);
        System.out.println("Updating Finished!");

    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Reading Courses... ID:" + theId);
        Course tmpCourse = appDAO.findCourseById(theId);
        System.out.println("Finished! - " + tmpCourse);

        System.out.println("Updating...");
        tmpCourse.setTitle("TEST1429");
        appDAO.update(tmpCourse);
        System.out.println("Update Finished!");
    }


    private void deleteCourse(AppDAO appDAO) {
        int theId = 11;
        System.out.println("Deleting Course... id:" + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        //create course
        Course tmpCourse = new Course("Dance - How to make big popping!");

        //add review
        tmpCourse.addReview(new Review("Good - by allen"));
        tmpCourse.addReview(new Review("Bad - by Farmer"));

        //save course
        //save review at the same time because of cascadeType
        System.out.println("Saving..." + tmpCourse);
        System.out.println("Saving..." + tmpCourse.getReviews());
        appDAO.save(tmpCourse);
        System.out.println("Done!");

    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int theId = 10;

        //retrieve data
        System.out.println("Reading...");
        Course tmpCourse = appDAO.findCourseAndReviewsById(theId);

        //print
        System.out.println("Finished! the course is " + tmpCourse.getTitle());
        System.out.println("And the reviews are" + tmpCourse.getReviews());
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Deleting ... the id is " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }
}
