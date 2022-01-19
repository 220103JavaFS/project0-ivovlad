package com.revature.models;

import java.util.Objects;

public class Account {
    private int number;
    private int clientID;
    private double balance;
    private String type;

    public Account() {
    }

    public Account(int number, int clientID, double balance, String type) {
        this.number = number;
        this.clientID = clientID;
        this.balance = balance;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return number == account.number && clientID == account.clientID && Double.compare(account.balance, balance) == 0 && Objects.equals(type, account.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, clientID, balance, type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", clientID=" + clientID +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
    }
}
