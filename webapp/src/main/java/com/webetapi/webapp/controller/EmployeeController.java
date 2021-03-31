package com.webetapi.webapp.controller;

import com.webetapi.webapp.CustomProperties;
import com.webetapi.webapp.model.Employee;
import com.webetapi.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;



import lombok.Data;

// NB le transfert de données vers les pages HTML ne marchent pas!

@Data
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private CustomProperties props;

    @GetMapping("/test")
    public String test (Model model) {
        return "test";
    }

    @RequestMapping("/printEmployee")
    @ResponseBody
    public Iterable<Employee>  home2() {
   /*     Iterable<Employee> listEmployee = service.getEmployees();
        return listEmployee;
    }*/
///TESTTT DE DEBUG ICI

        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {
                }
        );
        return response.getBody();
    }


    @GetMapping("/home")
    public String home(Model model) {
        Iterable<Employee> listEmployee = service.getEmployees();
        model.addAttribute("employees", listEmployee);
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