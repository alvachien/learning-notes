package com.alvachien;

public class NullInvocation {
    public static void info() {
        System.out.println("Entering info()");
    }

    public static void main(String[] args) {
        NullInvocation ni = null;
        ni.info();
    }    
}
