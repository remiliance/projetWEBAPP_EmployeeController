package com.webetapi.webapp.service;

import com.webetapi.webapp.model.Employee;
import com.webetapi.webapp.repository.EmployeeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.Data;

import java.util.List;

@Data
@Service
public class EmployeeService {

    @Autowired
    public EmployeeProxy employeeProxy;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee(id);
    }

    public List<Employee> getEmployees() {
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