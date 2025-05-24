package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

public class SwimCoach implements Coach{

    public SwimCoach(){
        System.out.println("In Constructor: "+getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Swim 100 meters as warm up EVERY DAY !!";
    }
}
