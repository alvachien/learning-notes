package com.alvachien;



public class AutoPromote {
    public static void main(String[] args) {
        short svalue = 5;

        // svalue = svalue - 2;
        svalue -= 2;

        byte b = 40;
        char c = 'a';
        int i = 23;
        double d = 3.13;

        double result = b + c + i + d;
        System.out.println(result);

        int val = 3;
        int intResult = 23 / val;
        System.out.println(intResult);

        System.out.println("Hello " + 'a' + 7);

        System.out.println('a' + 7 + "Hello");
    }
}
