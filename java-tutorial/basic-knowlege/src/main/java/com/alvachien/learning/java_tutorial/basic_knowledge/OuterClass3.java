package com.alvachien.learning.java_tutorial.basic_knowledge;

public class OuterClass3 {
    private static String NAME = "OUTER";

    private String name;

    OuterClass3(String name) {
        this.name = name;
    }

    static class StaticNested {
        void hello() {
            System.out.println("Hello, " + OuterClass3.NAME);
        }
    }
}
