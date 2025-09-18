package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyApiAspect {
    @Before("com.luv2code.aopdemo.aspect.luvExpression.forDaoNoGetterSetter()")
    public void performApi() {
        System.out.println("========= ## Perform API ===========");
    }
}
