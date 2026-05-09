package org.example;

public class EWallet {
    private double balance;
    private String pin;

    public EWallet(double balance, String pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public boolean login(String inputPin) {
        return pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public boolean cashIn(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean sendMoney(EWallet receiver, double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        receiver.balance += amount;
        return true;
    }
}