package com.springtest.demoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String homepage(){
        return "Welcome to Home Page";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

}
