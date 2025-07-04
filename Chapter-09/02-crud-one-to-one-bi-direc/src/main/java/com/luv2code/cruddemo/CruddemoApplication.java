package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createInstructor(appDAO);
//            findInstructor(appDAO);
//            deleteInstructor(appDAO);
//            findInstructorDetail(appDAO);
            deleteInstructorDetail(appDAO);
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
}
