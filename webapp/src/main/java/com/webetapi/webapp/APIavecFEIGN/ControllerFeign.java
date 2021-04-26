package com.webetapi.webapp.APIavecFEIGN;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ControllerFeign {

    @Autowired
    private MicroserviceEmployeeProxy microserviceEmployeeProxy;

    @RequestMapping("/")
    public String accueil(Model model){
        List<EmployeeBean> employees =  microserviceEmployeeProxy.listeDesEmployee();
       model.addAttribute("employees", employees);

        return "home";
    }

}
