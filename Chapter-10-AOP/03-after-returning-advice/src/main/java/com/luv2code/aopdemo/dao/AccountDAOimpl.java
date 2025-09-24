package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Account> findAccounts() {
        return this.findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean excHappen) {
        if (excHappen) throw new RuntimeException("U got an ERROR ~~!!");
        List<Account> result = new ArrayList<>();
        Account tmpAc1 = new Account("john", "gold");
        Account tmpAc2 = new Account("mary", "silver");
        result.add(tmpAc1);
        result.add(tmpAc2);
        return result;
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
