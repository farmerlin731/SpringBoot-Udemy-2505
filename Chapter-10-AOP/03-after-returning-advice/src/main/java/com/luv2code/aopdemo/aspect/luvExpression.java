package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

//If there r only @pointcut, the @Aspect is optional.
//@Aspect
public class luvExpression {
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

    @Pointcut("forDaoPackage() && !(forGetter()||forSetter())")
    public void forDaoNoGetterSetter() {
    }
}
