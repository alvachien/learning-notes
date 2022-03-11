package com.alvachien.learning.java_tutorial.basic_knowledge;

public class OuterClass2 {
    private String name;

    OuterClass2(String name) {
        this.name = name;
    }

    void asyncHello() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, " + OuterClass2.this.name);
            }
        };
        new Thread(r).start();
    }    
}
