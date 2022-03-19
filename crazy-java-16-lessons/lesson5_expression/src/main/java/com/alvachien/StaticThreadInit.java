package com.alvachien;

public class StaticThreadInit {
    static {
        Thread t = new Thread() {
            public void run() {
                System.out.println("Entering run() method");
                System.out.println(website);
                website = "www.test.com";
                System.out.println("Leaving run() method");
            }
        };

        t.start();
        try {
            // Test 1: The following line will freeze the call
            // t.join();

            // Test 2.
            Thread.sleep(1);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static String website = "www.origin.com";

    public static void main(String[] args) {
        System.out.println(StaticThreadInit.website);
    }
}
