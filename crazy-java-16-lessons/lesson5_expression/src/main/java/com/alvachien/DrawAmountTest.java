package com.alvachien;

class DrawAmountThread extends Thread {
    private Account account;
    private double drawAmount;

    public DrawAmountThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    public void run() {
        account.draw(drawAmount);
    }
}

public class DrawAmountTest {
    public static void main(String[] args) {
        Account acnt = new Account("123456", 1000);

        new DrawAmountThread("Test1", acnt, 800).start();
        new DrawAmountThread("Test2", acnt, 800).start();
    }   
}
