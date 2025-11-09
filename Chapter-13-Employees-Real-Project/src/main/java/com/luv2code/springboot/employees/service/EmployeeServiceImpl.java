package com.luv2code.springboot.employees.service;

import com.luv2code.springboot.employees.dao.EmployeeDAO;
import com.luv2code.springboot.employees.entity.Employee;
import com.luv2code.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeRepository) {
        employeeDao = theEmployeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(long theId) {

        Employee result = employeeDao.findById(theId);

//        Employee theEmployee = null;
//
//        if (result.isPresent()) {
//            theEmployee = result.get();
//        } else {
//            throw new RuntimeException("Did not find employee id - " + theId);
//        }

        return result;
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(0, employeeRequest);
        return employeeDao.save(theEmployee);
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(id, employeeRequest);
        return employeeDao.save(theEmployee);
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
        employeeDao.deleteById(theId);
    }
}
