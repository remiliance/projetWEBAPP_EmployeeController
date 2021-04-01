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

import java.util.Iterator;
import java.util.List;

// NB le transfert de données vers les pages HTML ne marchent pas!

@Data
@Controller
public class EmployeeController {

    @Autowired
    public EmployeeService service;


    @Autowired
    private CustomProperties props;



    @RequestMapping("/printEmployee")
    @ResponseBody
    public List<Employee>  home2() {
        List<Employee> listEmployee = service.getEmployees();
        return listEmployee;
    }


    @GetMapping("/home")
    public String home(Model model) {
        List<Employee> listEmployee =  service.getEmployees();
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
        Employee e = new Employee();
        e = service.getEmployee(id);
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