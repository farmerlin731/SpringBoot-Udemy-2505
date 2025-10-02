package com.luv2code.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneSeviceImpl implements TrafficFortuneSevice {

    @Override
    public String getFortune() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello Traffic Fortune :)";
    }

    @Override
    public String getFortune(boolean excHappen) {
        if (excHappen) throw new RuntimeException("Major Accident! Highway Closed!");

        return getFortune();
    }
}
