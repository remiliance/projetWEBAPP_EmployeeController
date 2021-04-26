package com.webetapi.webapp.controller;


import com.webetapi.webapp.CustomProperties;
import com.webetapi.webapp.model.Employee;
import com.webetapi.webapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;



import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @Autowired
    private CustomProperties props;



    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


//NE MARCHE PAS !
    @RequestMapping("/printEmployee")
    @ResponseBody
    public List<Employee>  home2() {
        return service.getEmployees();
    }
//Marche!!
    @RequestMapping("/printEmployee2")
    @ResponseBody
    public List<Employee>  home4() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>(){});
        return response.getBody();
    }

//idem : marche
    @GetMapping("/home")
    public String home(Model model) {

        logger.info("Home Called");

        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>(){});
        model.addAttribute("employees", response.getBody());
        return "home";
    }
// ne marche pas
    @GetMapping("/home2")
    public String home3 (Model model) {
        logger.info("Home2 Called #1");
        List<Employee> listEmployee = service.getEmployees();
        logger.info("Home2 Called #2");
        model.addAttribute("employees",listEmployee );
        logger.info("Home2 Called #3");
        return "home";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        Employee e = new Employee();
        model.addAttribute("employee", e);
        return "formNewEmployee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") final int id, Model model) {
        Employee e = service.getEmployee(id);
        model.addAttribute("employee", e);
        return "formUpdateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
        service.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        if(employee.getId() != null) {
            // Employee from update form has the password field not filled,
            // so we fill it with the current password.
            Employee current = service.getEmployee(employee.getId());
            employee.setPassword(current.getPassword());
        }
        service.saveEmployee(employee);
        return new ModelAndView("redirect:/");
    }

}