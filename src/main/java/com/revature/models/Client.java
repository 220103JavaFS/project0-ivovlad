package com.revature.models;

import java.util.Objects;

public class Client {
    private int ssn;
    private String firstName;
    private String lastName;
    private String address;
    private int bankTellerID;

    public Client() {
    }

    public Client(int ssn, String firstName, String lastName, String address, int bankTellerID) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.bankTellerID = bankTellerID;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBankTellerID() {
        return bankTellerID;
    }

    public void setBankTellerID(int bankTellerID) {
        this.bankTellerID = bankTellerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return ssn == client.ssn && bankTellerID == client.bankTellerID && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssn, firstName, lastName, address, bankTellerID);
    }

    @Override
    public String toString() {
        return "Client{" +
                "ssn=" + ssn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", bankTellerID=" + bankTellerID +
                '}';
    }
}
