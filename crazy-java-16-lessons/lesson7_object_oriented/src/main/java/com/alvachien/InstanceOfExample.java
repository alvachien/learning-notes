package com.alvachien;

public class InstanceOfExample {
    public static void main(String[] args) {
        Object hello = "Hello World!";

        System.out.println("hello isntanceof Object = " + (hello instanceof Object));
        System.out.println("hello isntanceof String = " + (hello instanceof String));
        System.out.println("hello isntanceof Math = " + (hello instanceof Math));

        String hello2 = "Hello World!";
        System.out.println("hello2 isntanceof Object = " + (hello2 instanceof Object));
        System.out.println("hello2 isntanceof String = " + (hello2 instanceof String));
        // System.out.println("hello2 isntanceof Math = " + (hello2 instanceof Math)); <= cannot compile
        System.out.println("hello2 isntanceof java.io.Serializable = " + (hello2 instanceof java.io.Serializable));
    }
    
}
