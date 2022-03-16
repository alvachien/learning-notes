package com.alvachien;

public class MutableString {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello ");
        System.out.println(sb);
        System.out.println(System.identityHashCode(sb));

        sb.append("Java");
        System.out.println(sb);
        System.out.println(System.identityHashCode(sb));

        sb.append("!");
        System.out.println(sb);
        System.out.println(System.identityHashCode(sb));
    }
}
