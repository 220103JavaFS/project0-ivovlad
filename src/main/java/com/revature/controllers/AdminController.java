package com.revature.controllers;

import io.javalin.Javalin;

public class AdminController extends ClientController{
    @Override
    public void addRouts(Javalin app) {
        app.get("/admin/{clientNumber}", super.showAccounts);
        app.patch("/admin/withdraw/{accNum-balance}",super.withdraw);
        app.patch("/admin/deposit/{accNum-balance}", super.deposit);
        app.patch("/admin/transfer/{accFrom-accTo-amount}", super.transfer);
    }
}
