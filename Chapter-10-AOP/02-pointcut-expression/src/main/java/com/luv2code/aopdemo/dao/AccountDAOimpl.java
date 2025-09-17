package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOimpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account theAccount) {
        System.out.println(getClass() + " : New Add Account! :)");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " : doWork()");
        return false;
    }


    public String getName() {
        System.out.println(getClass() + " : getName()" + this.name);
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " : setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " : getServiceCode() " + this.serviceCode);
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " : setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
