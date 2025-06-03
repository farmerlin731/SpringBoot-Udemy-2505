package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

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

    //get one employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee myEmployee = employeeService.findById(employeeId);

        if (myEmployee == null) {
            throw new RuntimeException("Employee is not found - " + employeeId);
        }
        return myEmployee;
    }

    //add one employee @ database
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // set the id=0 to force save a new item instead of updata
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //update
    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //delete
}
