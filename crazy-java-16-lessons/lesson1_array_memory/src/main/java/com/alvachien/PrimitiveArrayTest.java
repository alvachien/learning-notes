package com.alvachien;

public class PrimitiveArrayTest {
    public static void main(String[] args) {
        int[] iarr = null;
        System.out.println(iarr); // Without initial, it can be access.

        iarr = new int[5];
        System.out.println(iarr);
        System.out.println(iarr.length);
    }
}
