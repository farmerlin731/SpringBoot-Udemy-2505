package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    //define endpoint "/student" - return a list of student
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> myList = new ArrayList<>();
        myList.add(new Student("Farmer", "Lin"));
        myList.add(new Student("Bing", "Su"));
        myList.add(new Student("Ching", "Chen"));

        return myList;
    }
}
