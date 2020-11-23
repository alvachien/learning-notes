package com.alvachien.learning.java_tutorial.basic_knowledge;

public class OuterClass {
    private String name;

    OuterClass(String name) {
        this.name = name;
    }

    class InnerClass {
        String hello() {
            return "Hello, " + OuterClass.this.name;
        }
    }    
}
