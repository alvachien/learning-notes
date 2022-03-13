package com.alvachien;

public class FinalInitTest {
    final String str1;
    final String str2;
    final String str3 = "Java";
    {
        str1 = "Java";
    }
    public FinalInitTest() {
        str2 = "Java";
    }
    public void display() {
        System.out.println(str1 + str1 == "JavaJava"); // false
        System.out.println(str2 + str2 == "JavaJava"); // false
        System.out.println(str3 + str3 == "JavaJava"); // true
    }
    public static void main(String[] args){
        FinalInitTest test = new FinalInitTest();
        test.display();

    }
}
