package com.luv2code.springboot.employees.service;

import com.luv2code.springboot.employees.dao.EmployeeRespository;
import com.luv2code.springboot.employees.entity.Employee;
import com.luv2code.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRespository theEmployeeRespository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRespository theEmployeeRepository) {
        theEmployeeRespository = theEmployeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return theEmployeeRespository.findAll();
    }

    @Override
    public Employee findById(long theId) {

        Optional<Employee> result = theEmployeeRespository.findById(theId);

        Employee theEmployee = null;

        //Only part need to be modified.
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(0, employeeRequest);
        return theEmployeeRespository.save(theEmployee);
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(id, employeeRequest);
        return theEmployeeRespository.save(theEmployee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail());
    }

    @Transactional
    @Override
    public void deleteById(long theId) {
        theEmployeeRespository.deleteById(theId);
    }
}
