package com.alvachien;

public class CompositeAssign2 {
    public static void main(String[] args) {
        Object he = new CompositeAssign2();

        String test = "Test ";
        test += he;

        System.out.println(test);

        he += test;
    }
}
