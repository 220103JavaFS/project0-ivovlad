package com.revature.services;

import com.revature.models.Client;
import com.revature.repos.BankTellerDAOImpl;

public class BankTellerService {
    private BankTellerDAOImpl tellerDAO = new BankTellerDAOImpl();

    public boolean newClient(Client cl){return tellerDAO.newClient(cl);}

}
