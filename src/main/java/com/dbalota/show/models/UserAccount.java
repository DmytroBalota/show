package com.dbalota.show.models;

/**
 * Created by Dmytro_Balota on 3/30/2016.
 */
public class UserAccount {
    private long accountNumber;
    private long userId;
    private double balance;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
