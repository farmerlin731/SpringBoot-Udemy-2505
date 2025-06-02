package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> myList;

    //Set the fake data
    @PostConstruct
    public void loadData() {
        myList = new ArrayList<>();
        myList.add(new Student("Farmer", "Lin"));
        myList.add(new Student("Bing", "Su"));
        myList.add(new Student("Ching", "Chen"));
        myList.add(new Student("Allen", "Wang"));
    }

    //define endpoint "/student" - return a list of student
    @GetMapping("/students")
    public List<Student> getStudents() {
        return myList;
    }


    //define endpoint "/student/studentId" - return 'one' student
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        //Check the student ID.
        if (studentId >= myList.size() || (studentId < 0)) {
            throw new StudentNotFoundException("Student ID not found. the id is :" + studentId);
        }

        return myList.get(studentId);
    }

    //Add exception handler.
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        //Create student error response.
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
