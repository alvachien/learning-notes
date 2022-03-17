package com.alvachien;

public class StringReplace {
    public static void main(String[] args) {
        String str = "hello.world.again";
        String st2 = str.replace(".", "\\");
        System.out.println(st2);

        String st3 = str.replaceAll("\\.", "\\\\");
        System.out.println(st3);
    }
}
