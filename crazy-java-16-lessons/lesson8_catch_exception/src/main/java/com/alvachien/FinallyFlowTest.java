package com.alvachien;

public class FinallyFlowTest {
    public static void main(String[] args) {
        int a = test();
        System.out.println(a);
    }

    public static int test() {
        int count = 5;
        try {
            System.out.println("Enterring try");
            return 1;
        }
        finally {
            System.out.println("Enterring finally");
            return 2;
        }
    }
}
