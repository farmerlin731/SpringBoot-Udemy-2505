package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOimpl implements AccountDAO {


    @Override
    public void addAccount(Account theAccount) {
        System.out.println(getClass() + " : New Add Account! :)");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " : doWork()");
        return false;
    }


}
