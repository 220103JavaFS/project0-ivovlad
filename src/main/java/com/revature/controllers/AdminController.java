package com.revature.controllers;

import com.revature.models.Account;
import com.revature.services.BankAdminService;
import com.revature.services.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import static java.lang.Integer.parseInt;

public class AdminController extends ClientController{

    private BankAdminService adminService = new BankAdminService();

    Handler showAllAccounts = (ctx) -> {
//        ctx.result("Hello");
        ctx.json(adminService.showAllAccounts());
        ctx.status(200);
    };

    Handler newAccount = (ctx) -> {

            Account acc = ctx.bodyAsClass(Account.class);
//        ctx.result(String.valueOf(acc.getClientID()));
            if(adminService.newAccount(acc)){
                ctx.status(201);
            }else {

                ctx.status(400);
            }


    };


    @Override
    public void addRouts(Javalin app) {
        app.get("/admin/{clientNumber}", super.showAccounts);
        app.patch("/admin/withdraw/{accNum-balance}",super.withdraw);
        app.patch("/admin/deposit/{accNum-balance}", super.deposit);
        app.patch("/admin/transfer/{accFrom-accTo-amount}", super.transfer);
        app.get("/admin", showAllAccounts);
        app.post("/admin_new", newAccount);
    }
}
