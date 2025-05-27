package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
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
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
            readStudent(studentDAO);
        };
    }


    private void readStudent(StudentDAO studentDAO) {
        //create a student obj
        System.out.println("Creating new student obj...");
        Student tmpStu = new Student("Mary", "Sun", "mary@gmail.com");

        //save
        studentDAO.save(tmpStu);

        //display id
        int tmpID = tmpStu.getId();
        System.out.println("Already saved. The id is " + tmpID);

        //retrieve
        Student gotStu = studentDAO.findById(tmpID);

        //display student
        System.out.println("Found the student! -> " + gotStu);
    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Creating...");
        Student tmpStu = new Student("Farmer", "Lin", "tmp@gmail.com");

        System.out.println("Saving...");
        studentDAO.save(tmpStu);

        System.out.println("Finished! the saved student id :" + tmpStu.getId());
    }


}
