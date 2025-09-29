package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.service.TrafficFortuneSevice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(TrafficFortuneSevice theTrafficFortuneSevice) {
        return runner -> {
            testAroundAdvice(theTrafficFortuneSevice);
        };
    }

    public void testAroundAdvice(TrafficFortuneSevice theTrafficFortuneSevice) {
        String result = theTrafficFortuneSevice.getFortune();
        System.out.println("----------------------");
        System.out.println("In Main Function:");
        System.out.println("Result:" + result);
    }

    public void testFinallyAdvice(AccountDAO accountDAO) {
        List<Account> tmpList = null;
        try {
            boolean excHappen = false;
            tmpList = accountDAO.findAccounts(excHappen);
            System.out.println("----------------------");
            System.out.println("In Main Function:");
            System.out.println("Result:" + tmpList);
        } catch (Exception exc) {
            System.out.println("----------------------");
            System.out.println("In Main Function:");
            System.out.println("Exception:" + exc);
        }
    }


    public void testThrowingAdvice(AccountDAO accountDAO) {
        List<Account> tmpList = null;
        try {
            boolean excHappen = true;
            tmpList = accountDAO.findAccounts(excHappen);
            System.out.println("----------------------");
            System.out.println("In Main Function:");
            System.out.println("Result:" + tmpList);
        } catch (Exception exc) {
            System.out.println("----------------------");
            System.out.println("In Main Function:");
            System.out.println("Exception:" + exc);
        }
    }

    public void testAfterAdvice(AccountDAO accountDAO) {
        List<Account> tmpList = accountDAO.findAccounts();
        System.out.println("----------------------");
        System.out.println("In Main Function:");
        System.out.println("Result:" + tmpList);
    }


}
