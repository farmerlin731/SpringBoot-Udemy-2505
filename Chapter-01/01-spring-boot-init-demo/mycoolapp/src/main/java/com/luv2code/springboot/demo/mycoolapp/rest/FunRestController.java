package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    //Inject properties @ application properties.
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    //expose "/" that return "Hello world :)"
    @GetMapping("/")
    public String sayHello(){
        return "Hello world :)\nThe Coach is "+coachName+", and the team is "+teamName;
    }

    // expose a new endpoint
    @GetMapping("/practice")
    public String doSomeWork(){
        return "I am practicing Dev-Tool right now ~~~!!";
    }
}
