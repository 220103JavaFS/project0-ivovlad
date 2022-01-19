package com.revature.repos;

import com.revature.models.Account;
import com.revature.models.Client;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public List<Account> showMyAccounts(int clientNum) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM accounts WHERE accounts.client_id ="+clientNum+";";

            Statement statement = conn.createStatement(); // we need this object for executing the query

            ResultSet result = statement.executeQuery(sql); // this object holds the result returned for our query

            List<Account> list = new ArrayList<>();

            while(result.next()){
                Account acc = new Account();
//                Client cl = new Client();
                acc.setNumber(result.getInt("acc_number"));
                acc.setClientID(result.getInt("client_id"));
                acc.setBalance(result.getDouble("balance"));
                acc.setType(result.getString("acc_type"));
//                cl.setAddress(result.getString("address"));
//                cl.setBankTellerID(result.getInt("bank_teller_id"));
//                cl.setFirstName(result.getString("first_name"));
//                cl.setLastName(result.getString("last_name"));

                list.add(acc);

            }

            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();//return new ArrayList<>();
    }

    @Override
    public boolean withdraw(int acc, double amount) {

        try (Connection conn = ConnectionUtil.getConnection()) {
//            String sqlGetBalance = "SELECT balance FROM accounts WHERE acc_number=?;";
//            PreparedStatement statement = conn.prepareStatement(sqlGetBalance);
//            statement.setInt(1,acc);
//            ResultSet result = statement.executeQuery();
//            if (result.getDouble("balance")<amount){
//                return false;
//            } else{
                String sql = "UPDATE accounts SET balance = balance-? WHERE acc_number = ?;";
                PreparedStatement s = conn.prepareStatement(sql);
                int count = 0;
                s.setDouble(++count, amount);
                s.setInt(++count, acc);
                s.execute();
                return true;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deposit(int acc, double amount) {
        try (Connection conn = ConnectionUtil.getConnection()) {
//
            String sql = "UPDATE accounts SET balance = balance+? WHERE acc_number = ?;";
            PreparedStatement s = conn.prepareStatement(sql);
            int count = 0;
            s.setDouble(++count, amount);
            s.setInt(++count, acc);
            s.execute();
            return true;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean transfer(int accFrom, int accTo, double amount) {
        try (Connection conn = ConnectionUtil.getConnection()) {
//
            String sql = "BEGIN;\n" +
                    "UPDATE accounts SET balance = balance+? WHERE acc_number = ?;\n" +
                    "\n" +
                    "UPDATE accounts SET balance = balance-? WHERE acc_number = ?;\n" +
                    "COMMIT; ";
            PreparedStatement s = conn.prepareStatement(sql);
            int count = 0;
            s.setDouble(++count, amount);
            s.setInt(++count, accTo);
            s.setDouble(++count, amount);
            s.setInt(++count, accFrom);
            s.execute();
            return true;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
