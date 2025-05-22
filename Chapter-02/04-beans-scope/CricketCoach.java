package com.luv2code.springcoredemo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Lazy
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("Cricket Coach Init!");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice bowling for 15 mins every day.";
    }
}
