package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    //Mapping to show initial form.
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    //Mapping to process the HTML form.
    @GetMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }


}
