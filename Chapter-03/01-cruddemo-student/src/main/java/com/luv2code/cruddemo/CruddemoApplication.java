package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
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
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
//            readStudent(studentDAO);
//            queryForStudents(studentDAO);
//            queryStudentsByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
            deleteAllStudent(studentDAO);
        };
    }


    private void queryStudentsByLastName(StudentDAO studentDAO) {
        //get a list
        List<Student> theStudents = studentDAO.findByLastName("Lin");
        //display
        for (Student tmpStu : theStudents) {
            System.out.println(tmpStu);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        //get a list
        List<Student> theStudents = studentDAO.findAll();
        //display
        for (Student tmpStu : theStudents) {
            System.out.println(tmpStu);
        }
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

    private void updateStudent(StudentDAO studentDAO) {
        System.out.println("Updating...");

        //retrieve
        int stuID = 1;
        Student myStu = studentDAO.findById(stuID);

        //update
        myStu.setFirstName("George");
        studentDAO.update(myStu);

        //display
        Student newStu = studentDAO.findById(stuID);
        System.out.println("Result - id:" + stuID + ", and the student is" + newStu);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int tmpID = 4;
        studentDAO.delete(tmpID);
        System.out.println("Deleted ID is " + tmpID);
    }

    private void deleteAllStudent(StudentDAO studentDAO) {
        int rowsDel = studentDAO.deleteAll();
        System.out.println("All Delete! the num or deleted rows is " + rowsDel);
    }
}
