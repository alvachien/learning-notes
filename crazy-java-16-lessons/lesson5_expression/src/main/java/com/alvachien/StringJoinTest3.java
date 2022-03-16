package com.alvachien;

public class StringJoinTest3 {
    public static void main(String[] args) {
        String str1 = "Hello Java length = 10";
        final String s1 = "Hello ";
        String str2 = s1 + "Java " + "length = 10";
        System.out.println(str1 == str2);

        final int len = 10;
        String str3 = "Hello " + "Java " + "length = " + len;
        System.out.println(str1 == str3);
    }
}
