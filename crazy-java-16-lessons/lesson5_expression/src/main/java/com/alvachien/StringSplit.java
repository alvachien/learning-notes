package com.alvachien;

public class StringSplit {
    public static void main(String[] args) {
        String str = "hello.world.again";
        String[] arstrs = str.split("\\.");
        for(String a: arstrs) {
            System.out.println(a);
        }
    }
}
