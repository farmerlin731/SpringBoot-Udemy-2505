package com.luv2code.springcoredemo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("Cricket Coach Init!");
    }

    // define init method
    @PostConstruct
    public void doStartupStuff(){
        System.out.println("In doStartupStuff():"+getClass().getSimpleName());
    }

    // define destroy method
    @PreDestroy
    public void doCleanStuff(){
        System.out.println("In doCleanStuff():"+getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice bowling for 15 mins every day.";
    }
}
