package com.webetapi.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Session {


    @GetMapping("/session")
    public String connection() {
        return "jsp/session.jsp";
    }
}

