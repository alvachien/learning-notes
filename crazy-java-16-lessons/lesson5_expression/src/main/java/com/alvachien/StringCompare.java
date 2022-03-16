package com.alvachien;

public class StringCompare {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("z");
        String s3 = new String("abc");
        if (s1.compareTo(s3) == 0) {
            System.out.println(s1 + " == " + s3);
        }
        if (s1.compareTo(s2) < 0) {
            System.out.println(s1 + " < " +s2);
        }

        System.out.println("s1.equalss(s3) = " + s1.equals(s3));
        System.out.println("s1 == s3: " + (s1 == s3));
    }
}
