package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
        return runner -> {
            demoTheBeforeAdvice(accountDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO) {
        //method 1
        Account tmpAccount = new Account("Farmer", "Golden");
        theAccountDAO.addAccount(tmpAccount);
        //method 2
        theAccountDAO.doWork();

        //call getter & setter
        theAccountDAO.setName("Harry");
        theAccountDAO.getName();
        theAccountDAO.setServiceCode("CODE3345678");
        theAccountDAO.getServiceCode();
    }

}
