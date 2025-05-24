package com.luv2code.springcoredemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("hahacustom")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
