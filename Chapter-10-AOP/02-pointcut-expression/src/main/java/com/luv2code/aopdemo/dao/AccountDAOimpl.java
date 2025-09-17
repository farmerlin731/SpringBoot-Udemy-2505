package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOimpl implements AccountDAO {
    @Override
    public void addAccount() {
        System.out.println(getClass() + " : Hello - Create Account! :)");
    }

    @Override
    public void addSillyMember() {
        System.out.println(getClass() + " : Hello - Create Member! :)");
    }

    @Override
    public void addAccount(Account theAccount) {
        System.out.println(getClass() + " : New Add Account! :)");
    }
}
