package com.alvachien;

public class ConstructorExample {

    private String name;
    public ConstructorExample(String name) {
        // this.name = name;
    }
    public void ConstructorExample(String name) {
        this.name = name;
    }

    public ConstructorExample ConstructorExample() {
        return this;
    }

    public String getName() {
        return this.name;
    }

    public static void main(String[] args) {
        ConstructorExample obj = new ConstructorExample("Test");
        System.out.println(obj.getName());
    }

}
