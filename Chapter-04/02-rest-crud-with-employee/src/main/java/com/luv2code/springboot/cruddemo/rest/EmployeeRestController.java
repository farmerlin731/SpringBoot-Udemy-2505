package com.luv2code.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeServiceImpl employeeService;
    private ObjectMapper objectMapper;

    //quick and dirty : inject employee dao
    public EmployeeRestController(EmployeeServiceImpl theEmployeeServiceImpl, ObjectMapper theObjectMapper) {
        this.employeeService = theEmployeeServiceImpl;
        this.objectMapper = theObjectMapper;
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

    //patch - partial update
    @PatchMapping("employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String, Object> patchPayload) {

        Employee tmpEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if (tmpEmployee == null) {
            throw new RuntimeException("Employee not found! id: " + employeeId);
        }

        // throw exception if payload contain id
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("ID is not allowed in request body.");
        }

        Employee patchedEmployee = apply(patchPayload, tmpEmployee);

        Employee dbEmployee = employeeService.save(patchedEmployee);
        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tmpEmployee) {
        //convert from object to JSON
        ObjectNode employeeNode = objectMapper.convertValue(tmpEmployee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //Merge
        employeeNode.setAll(patchNode);

        //convert back
        return objectMapper.convertValue(employeeNode, Employee.class);

    }


    //delete
}
