package org.example;

public class EWallet {
    private double balance;
    private String pin;


     //Constructor: This is called when you create a brand new EWallet object.
     //It sets up the initial money and the user's password.
    public EWallet(double balance, String pin) {
        this.balance = balance;
        this.pin = pin;
    }

    //Checks if the user entered the correct PIN to access their account.
    public boolean login(String inputPin) {
        return pin.equals(inputPin);
    }

    //Allows the user to check how much money they currently have.
    public double getBalance() {
        return balance;
    }

    //Adds money to the wallet (like depositing cash).
    public boolean cashIn(double amount) {
        // Security check: You cannot deposit zero or negative money.
        if (amount <= 0) return false;// Cash-in failed
        balance += amount;// Add the valid amount to the current balance.
        return true;// Cash-in successful
    }

    //Transfers money from this wallet to another person's wallet.
    public boolean sendMoney(EWallet receiver, double amount) {
        // Security check:
        // 1. You cannot send zero or negative money.
        // 2. You cannot send more money than you actually have.
        if (amount <= 0 || amount > balance) return false;// Transfer failed
        balance -= amount;// Deduct the money from the sender's balance
        receiver.balance += amount;// Add the same amount of money to the receiver's balance
        return true;// Transfer successful
    }
}