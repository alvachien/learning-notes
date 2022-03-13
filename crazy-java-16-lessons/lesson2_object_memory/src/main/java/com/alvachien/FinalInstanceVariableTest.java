package com.alvachien;

public class FinalInstanceVariableTest {
    final int var1 = "test".length();
    final int var2;
    final int var3;

    {
        var2 = "test2".length();
    }

    public FinalInstanceVariableTest() {
        this.var3 = "test3".length();
    }

    public static void main(String[] args){
        FinalInstanceVariableTest obj = new FinalInstanceVariableTest();
        System.out.println(obj.var1);
        System.out.println(obj.var2);
        System.out.println(obj.var3);
    }
}
