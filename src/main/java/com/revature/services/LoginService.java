package com.revature.services;

public class LoginService {

    public boolean login(String username, String password){
        if((username.equals("client")&&password.equals("clientpass")) ||
                (username.equals("admin")&&password.equals("adminpass"))  ||
                (username.equals("teller")&&password.equals("tellerpass"))){
            return true;
        }else{
            return false;
        }
    }
}
