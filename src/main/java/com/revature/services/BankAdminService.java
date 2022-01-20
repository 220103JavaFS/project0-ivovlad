package com.revature.services;

import com.revature.models.Account;
import com.revature.repos.BankAdminDAOImpl;
import com.revature.repos.ClientDAO;
import com.revature.repos.ClientDAOImpl;

import java.util.List;

public class BankAdminService {
    private BankAdminDAOImpl adminDAO = new BankAdminDAOImpl();

    public List<Account> showAllAccounts(){return adminDAO.showAllAccounts();}
    public boolean newAccount(Account acc){return adminDAO.newAccount(acc);}
    public boolean deleteAccount(int number){return adminDAO.deleteAccount(number);}
    public boolean editAccount(Account acc){return adminDAO.editAccount(acc);}
    }

