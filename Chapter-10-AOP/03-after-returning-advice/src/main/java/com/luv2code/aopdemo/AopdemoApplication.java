package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
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
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
        return runner -> {
            testAfterAdvice(accountDAO);
        };
    }

    public void testAfterAdvice(AccountDAO accountDAO) {
        List<Account> tmpList = accountDAO.findAccounts();
        System.out.println("----------------------");
        System.out.println("In Main Function:");
        System.out.println("Result:" + tmpList);

    }
}
