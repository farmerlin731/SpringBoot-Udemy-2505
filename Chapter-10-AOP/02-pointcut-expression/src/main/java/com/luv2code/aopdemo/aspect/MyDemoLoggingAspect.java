package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(MyDemoLoggingAspect.class);

    @Before("com.luv2code.aopdemo.aspect.luvExpression.forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("========= @@ Before Accout Advice ===========");

        //display the method signature
        MethodSignature tmpMethodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println(tmpMethodSignature);

        //display method arguments

        //get args
        Object[] objs = theJoinPoint.getArgs();

        //loop
        for (Object tmpObj : objs) {
            if (tmpObj instanceof Account tmpAc)
                System.out.println("Name: " + tmpAc.getName() + ", and Level: " + tmpAc.getLevel());
        }
    }
}
