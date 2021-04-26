package com.webetapi.webapp.APIavecFEIGN;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "api-elk-examples", url = "localhost:9001")
public interface MicroserviceEmployeeProxy {

        @GetMapping(value = "/employees") //iso restcontroller
        List<EmployeeBean> listeDesEmployee();

        @GetMapping( value = "/employee/{id}")
        EmployeeBean recupererUnEmployee(@PathVariable("id") int id);

}


