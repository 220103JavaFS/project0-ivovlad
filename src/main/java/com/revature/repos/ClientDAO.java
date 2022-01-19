package com.revature.repos;

import com.revature.models.Account;
import com.revature.models.Client;

import java.util.List;

public interface ClientDAO {
    public List<Account> showMyAccounts(int clientNum);
    public boolean withdraw(int acc, double amount);
    public boolean deposit(int acc, double amount);
    public boolean transfer(int accFrom, int accTo, double amount);

}
