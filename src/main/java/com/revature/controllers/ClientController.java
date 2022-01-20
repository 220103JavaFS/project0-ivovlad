package com.revature.controllers;

import com.revature.services.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import static java.lang.Integer.parseInt;


public class ClientController implements Controller{
    private ClientService clientService = new ClientService();

    Handler showAccounts = (ctx) -> {
        if(ctx.req.getSession(false)!=null&&(ctx.sessionAttribute("admin")== "adm"||
                                                    ctx.sessionAttribute("client")== "cl")){
        int clientNumber = parseInt(ctx.pathParam("clientNumber"));
        ctx.json(clientService.showMyAccounts(clientNumber));//ctx.result(clientService.showAccounts());//
        ctx.status(200);
        }else {
            ctx.status(401);
        }
    };

    Handler withdraw = (ctx) -> {
        if(ctx.req.getSession(false)!=null&&(ctx.sessionAttribute("admin")== "adm"||
                ctx.sessionAttribute("client")== "cl")){
        String[] params = ctx.pathParam("accNum-balance").split("_");
        int accNum = Integer.parseInt(params[0]);
        Double bal = Double.parseDouble(params[1]);
//        ctx.result("first parameter is"+accNum+"; second param is"+bal);
        if(clientService.withdraw(accNum,bal)){
            ctx.status(202);
        } else{
            ctx.result("clientService.withdraw(accNum,bal) returned false; Parameters supplied:accNum="+accNum+"; bal="+bal);
            ctx.status(400);
        }
        }else {
            ctx.status(401);
        }
    };

    Handler deposit = (ctx) -> {
        if(ctx.req.getSession(false)!=null&&(ctx.sessionAttribute("admin")== "adm"||
                ctx.sessionAttribute("client")== "cl")){
        String[] params = ctx.pathParam("accNum-balance").split("_");
        int accNum = Integer.parseInt(params[0]);
        Double bal = Double.parseDouble(params[1]);
//        ctx.result("first parameter is"+accNum+"; second param is"+bal);
        if(clientService.deposit(accNum,bal)){
            ctx.status(202);
        } else{
            ctx.result("clientService.deposit(accNum,bal) returned false; Parameters supplied:accNum="+accNum+"; bal="+bal);
            ctx.status(400);
        }
        }else {
            ctx.status(401);
        }
    };

    Handler transfer = (ctx) -> {
        if(ctx.req.getSession(false)!=null&&(ctx.sessionAttribute("admin")== "adm"||
                ctx.sessionAttribute("client")== "cl")){
        String[] params = ctx.pathParam("accFrom-accTo-amount").split("_");
        int accFrom = Integer.parseInt(params[0]);
        int accTo = Integer.parseInt(params[1]);
        Double amount = Double.parseDouble(params[2]);
//        ctx.result("first parameter is"+accNum+"; second param is"+bal);
        if(clientService.transfer(accFrom,accTo,amount)){
            ctx.status(202);
        } else{
            ctx.result("clientService.transfer(accFrom,accTo,bal) returned false; Parameters supplied:accFrom="+accFrom+"; accTo="+accTo+"; amount"+amount);
            ctx.status(400);
        }
        }else {
            ctx.status(401);
        }
    };

    @Override
    public void addRouts(Javalin app) {
        app.get("/client/{clientNumber}", showAccounts);
        app.patch("/client/withdraw/{accNum-balance}",withdraw);
        app.patch("/client/deposit/{accNum-balance}", deposit);
        app.patch("/client/transfer/{accFrom-accTo-amount}", transfer);
    }
}
