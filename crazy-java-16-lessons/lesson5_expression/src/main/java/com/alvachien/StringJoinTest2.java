package com.alvachien;

public class StringJoinTest2 {
    public static void main(String[] args) {
        String str1 = "Hello Java length = 10";
        String str2 = "Hello Java" + " length = " + "Hello Java".length();
        System.out.println(str1 == str2);
        int len = 10;
        String str3 = "Hello " + "Java" + " length = " + len;
        System.out.println(str1 == str3);
    }
}
