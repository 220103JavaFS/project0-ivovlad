package com.revature.repos;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAdminDAOImpl extends ClientDAOImpl{
    public List<Account> showAllAccounts() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM accounts;";

            Statement statement = conn.createStatement(); // we need this object for executing the query

            ResultSet result = statement.executeQuery(sql); // this object holds the result returned for our query

            List<Account> list = new ArrayList<>();

            while(result.next()){
                Account acc = new Account();
//
                acc.setNumber(result.getInt("acc_number"));
                acc.setClientID(result.getInt("client_id"));
                acc.setBalance(result.getDouble("balance"));
                acc.setType(result.getString("acc_type"));
//

                list.add(acc);

            }

            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean newAccount(Account acc) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO  accounts (client_id, balance,acc_type) VALUES (?,?,?);";

            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;
            statement.setInt(++count, acc.getClientID());
            statement.setDouble(++count, acc.getBalance());
            statement.setString(++count, acc.getType());


            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(int accNumber) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "DELETE  FROM  accounts \n" +
                    "WHERE acc_number = ?;";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, accNumber);

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean editAccount(Account acc) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "UPDATE accounts SET acc_type=? WHERE acc_number = ?;";

            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, acc.getType());
            statement.setInt(++count, acc.getNumber());


            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
