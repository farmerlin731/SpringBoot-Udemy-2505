package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeServiceImpl employeeService;

    //quick and dirty : inject employee dao
    public EmployeeRestController(EmployeeServiceImpl theEmployeeServiceImpl) {
        this.employeeService = theEmployeeServiceImpl;
    }

    //expose"/emplyees" and return a list
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }


}
