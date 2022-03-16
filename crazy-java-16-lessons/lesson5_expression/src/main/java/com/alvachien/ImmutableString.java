package com.alvachien;

public class ImmutableString {
    public static void main(String[] args) {
        String str = "Hello";
        System.out.println(System.identityHashCode(str));

        str = str + "Java";
        System.out.println(System.identityHashCode(str));

        str = str + "!";
        System.out.println(System.identityHashCode(str));
    }
}
