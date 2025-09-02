package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOimpl implements AccountDAO {
    @Override
    public void addAccount() {
        System.out.println(getClass() + " : Hello - Create Account! :)");
    }
}
