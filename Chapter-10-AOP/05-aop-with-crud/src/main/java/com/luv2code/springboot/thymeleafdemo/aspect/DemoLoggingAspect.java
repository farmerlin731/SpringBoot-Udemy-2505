package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declaration
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    public void forControllerPackage() {
    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    public void forServicePackage() {
    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
    public void forAppFlow() {

    }

    @Before("forAppFlow()")
    public void beforeAdvice(JoinPoint theJoinPoint) {
        String theMethods = theJoinPoint.getSignature().toShortString();
        myLogger.info("======> In @Before Advice, the method is :" + theMethods);

        //show args
        Object[] args = theJoinPoint.getArgs();
        for (Object arg : args) {
            myLogger.info("======> the arg is : " + arg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    public void afterAdvice(JoinPoint theJoinPoint, Object theResult) {
        String theMethods = theJoinPoint.getSignature().toShortString();
        myLogger.info("======> In @After Advice, the method is from :" + theMethods);

        myLogger.info("======> the result is : " + theResult);
    }
}
