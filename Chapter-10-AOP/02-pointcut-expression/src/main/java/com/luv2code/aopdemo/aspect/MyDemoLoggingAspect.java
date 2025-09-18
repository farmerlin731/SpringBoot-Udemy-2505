package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {

    @Before("com.luv2code.aopdemo.aspect.luvExpression.forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("========= @@ Before Accout Advice ===========");
    }


}
