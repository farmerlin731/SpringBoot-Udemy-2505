package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    // getter
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    private void forGetter() {
    }

    // setter
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    private void forSetter() {
    }

    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n========= Executing @Before Aspect ===========");
    }

    @Before("forDaoPackage()")
    public void performApi() {
        System.out.println("========= Perform API ===========");
    }

    @Before("forDaoPackage() && !(forGetter()||forSetter())")
    public void beforeDaoExcludeGetAndSet() {
        System.out.println("========= @Before Dao Exclude Getter & Setter ===========");
    }

}
