package com.luv2code.springcoredemo;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Lazy
//@Primary
public class BacketballCoach implements Coach {
    public BacketballCoach() {
        System.out.println("Basketball Coach Init!");
    }

    @Override
    public String getDailyWorkout() {
        return "Play basketball twice a week ~~!!";
    }
}
