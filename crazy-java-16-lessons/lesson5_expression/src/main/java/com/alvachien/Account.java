package com.alvachien;

public class Account {
    private String accountNo;
    private double balance;
    public Account() {        
    }
    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return this.balance;
    }
    public synchronized void draw(double drawAmount) {
        if (balance >= drawAmount) {
            System.out.println(Thread.currentThread().getName() + ", Draw amount: " + drawAmount);

            balance -= drawAmount;
            System.out.println("New Balance is " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + ", Draw failed!");
        }
    }

    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == Account.class) {
            Account target = (Account)obj;
            return target.accountNo.equals(this.accountNo);
        }
        return false;
    }
}
