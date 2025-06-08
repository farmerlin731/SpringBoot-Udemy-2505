package com.luv2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //read from dat & add data to model
    @GetMapping("/processFormV2")
    public String letShoutDude(HttpServletRequest req, Model model) {
        //read the request param
        String theName = req.getParameter("studentName");
        //convert the data to all caps
        theName = theName.toUpperCase();
        //create the message
        String result = "YOYO!" + theName;
        //add message
        model.addAttribute("message", result);
        return "helloworld";
    }

}
