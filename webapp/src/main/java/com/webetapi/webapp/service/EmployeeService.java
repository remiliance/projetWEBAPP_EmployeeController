package com.webetapi.webapp.service;

import com.webetapi.webapp.model.Employee;
import com.webetapi.webapp.repository.EmployeeProxy;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.Data;

import java.util.List;


@Service
public class EmployeeService {


    @Autowired
    private EmployeeProxy employeeProxy;

    Logger log;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee(id);
    }

    public List<Employee> getEmployees() {
        log.info("get Employees Called from Employee Service");
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final int id) {
        employeeProxy.deleteEmployee(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;

        // Functional rule : Last name must be capitalized.
        employee.setLastName(employee.getLastName().toUpperCase());

        if(employee.getId() == null) {
            // If id is null, then it is a new employee.
            savedEmployee = employeeProxy.createEmployee(employee);
        } else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }

        return savedEmployee;
    }

}