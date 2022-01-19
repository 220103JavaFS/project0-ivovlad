package com.revature.services;

import com.revature.models.Account;
import com.revature.repos.ClientDAO;
import com.revature.repos.ClientDAOImpl;

import java.util.List;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAOImpl();

    public List<Account> showMyAccounts(int clientNum){return clientDAO.showMyAccounts(clientNum);}
    public boolean withdraw(int acc, double amount) {
        if (acc < 0 || amount <= 0) {
            return false;
        } else {
            return clientDAO.withdraw(acc, amount);
        }
    }
    public boolean deposit(int acc, double amount) {
        if (acc < 0 || amount <= 0) {
            return false;
        } else {
            return clientDAO.deposit(acc, amount);
        }
    }


    public boolean transfer(int accFrom, int accTo, double amount) {
        if (accFrom < 0 || accTo < 0 || amount <= 0) {
            return false;
        } else {
            return clientDAO.transfer(accFrom, accTo, amount);
        }
    }

}
