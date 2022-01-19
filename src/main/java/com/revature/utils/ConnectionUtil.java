package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://project0.cmhpfqeepenq.us-east-1.rds.amazonaws.com:5432/postgres";
        String username = "postgres"; //System.getenv("uid");
        String password = "Aa111111";//System.getenv("pwd");

        return DriverManager.getConnection(url,username,password);
    }

//    public static void main(String[] args){
//        try{
//            getConnection();
//            System.out.println("Connection successful");
//        }catch (SQLException e){
//            System.out.println("Connection failed");
//            e.printStackTrace();
//        }
//    }
}
