package com.revature;

import com.revature.controllers.*;
import io.javalin.Javalin;

public class App {
    private static Javalin app;

    public static void main(String[] args) {
        app = Javalin.create();
        configure(new ClientController(), new AdminController(), new TellerController(), new LoginController());
        app.start(7777);
    }

    public static void configure(Controller... controllers){
        for (Controller c:controllers){
            c.addRouts(app);
        }
    }
}
