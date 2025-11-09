package com.luv2code.springboot.employees.controller;

import com.luv2code.springboot.employees.entity.Employee;
import com.luv2code.springboot.employees.service.EmployeeService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee getSingleEmployee(@PathVariable @Min(value = 1) long id) {
        Employee theEmployee = employeeService.findById(id);
        return theEmployee;
    }
}
