package com.alvachien;

class Base {
    private int i = 2;
    public Base() {
        System.out.println("Now the logic in " + this.getClass());
        System.out.println("Base i = " + i);
        this.Display();
    }
    public void Display() {
        System.out.println(i);
    }
}

class Derive extends Base {
    private int i = 22;
    public Derive() {
        System.out.println("Now the logic in " + this.getClass());
        System.out.println("Child i = " + i);
        i = 222;
    }
    public void Display() {
        System.out.println(i);
    }
}

public class BaseDeriveTest {
    public static void main(String[] args) {
        new Derive();
    }
}
