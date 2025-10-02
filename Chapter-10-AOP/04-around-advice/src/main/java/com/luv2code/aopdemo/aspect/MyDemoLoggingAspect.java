package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.*(..))")
    public Object aroundFortuneAdvice(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        System.out.println("----------------------");
        System.out.println("In Around AOP:");
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = "Major Accident! Highway Closed! But...AOP already handle it !";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Duration is : " + (endTime - startTime) / 1000 + " seconds.");
        return result + " (through AOP aspect)";
    }

    @After("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void afterFinallyAccountAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("----------------------");
        System.out.println("In After-Finally AOP:");
        System.out.println("Method:" + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.*.*(..))",
            throwing = "theExc")
    public void afterThrowingAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("----------------------");
        System.out.println("In After-Throwing AOP:");
        System.out.println("Method:" + method);
        System.out.println("Exception:" + theExc);
    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.*.*(..))",
            returning = "result")
    public void afterReturningAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("----------------------");
        System.out.println("In After-Returning AOP:");
        System.out.println("Method:" + method);
        System.out.println("Result:" + result);

        //Convert the data.
        for (Account ac : result) {
            String newName = ac.getName().toUpperCase();
            ac.setName(newName);
        }
    }

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
