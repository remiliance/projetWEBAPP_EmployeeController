package com.webetapi.webapp.repository;

import com.webetapi.webapp.CustomProperties;
import com.webetapi.webapp.model.Employee;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Component
public class EmployeeProxy {

    public EmployeeProxy() {
    }

    @Autowired
    private CustomProperties props;

    Logger log;

    /**
     * Get all employees
     * @return An iterable of all employees
     */
    public List<Employee> getEmployees() {

        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        );

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an employee by the id
     * @param id The id of the employee
     * @return The employee which matches the id
     */
    public Employee getEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );

        log.debug("Get Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new employee
     * @param e A new employee (without an id)
     * @return The employee full filled (with an id)
     */
    public Employee createEmployee(Employee e) {
        String baseApiUrl = props.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class);

        log.debug("Create Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an employee - using the PUT HTTP Method.
     * @param e Existing employee to update
     */
    public Employee updateEmployee(Employee e) {
        String baseApiUrl = props.getApiUrl();
        String updateEmployeeUrl = baseApiUrl + "/employee/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class);

        log.debug("Update Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Delete an employee using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The employee to delete
     */
    public void deleteEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Employee call " + response.getStatusCode().toString());
    }

}
