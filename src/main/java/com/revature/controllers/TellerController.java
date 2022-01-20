package com.revature.controllers;

import com.revature.models.Account;
import com.revature.models.Client;
import com.revature.services.BankTellerService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class TellerController implements Controller{

    BankTellerService tellerService = new BankTellerService();

    Handler newClient = (ctx) -> {
        if(ctx.req.getSession(false)!=null&&ctx.sessionAttribute("teller")== "tlr"){
        Client cl = ctx.bodyAsClass(Client.class);

        if(tellerService.newClient(cl)){
            ctx.status(202);
        }else {
            ctx.status(400);
        }
        }else {
            ctx.status(401);
        }

    };

    @Override
    public void addRouts(Javalin app) {
        app.post("/teller_new",newClient);
    }
}
