package com.revature.repos;

import com.revature.models.Account;
import com.revature.models.Client;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankTellerDAOImpl {

    public boolean newClient(Client cl) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO clients (ssn, first_name, last_name, address, bank_teller_id) \n"+
                            "VALUES (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;
            statement.setInt(++count, cl.getSsn());
            statement.setString(++count, cl.getFirstName());
            statement.setString(++count, cl.getLastName());
            statement.setString(++count, cl.getAddress());
            statement.setInt(++count, cl.getBankTellerID());


            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
