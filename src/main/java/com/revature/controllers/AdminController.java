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

    Handler deleteAccount = (ctx) -> {
        String accNumStr = ctx.pathParam("accNumber");
        int accNumber = Integer.parseInt(accNumStr);
        ctx.result("Status delete account:"+adminService.deleteAccount(accNumber));
//        ctx.json(adminService.showAllAccounts());
        ctx.status(200);
    };

    Handler editAccount = (ctx) -> {

            Account acc = ctx.bodyAsClass(Account.class);

            if(adminService.editAccount(acc)){
                ctx.status(202);
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
        app.delete("/admin/delete/{accNumber}", deleteAccount);
        app.patch("/admin_edit", editAccount);

    }
}
