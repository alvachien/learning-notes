package com.alvachien;

public class Cat {
    String name;
    int age;

    public Cat(String name, int age)
    {
        System.out.println("Entering Constructor");
        this.name = name;
        this.age = age;
    }
    {
        System.out.println("Entering no static fields");
        weight = 2.0;
    }
    double weight = 2.3;
    public String toString() {
        return "Cat[" + this.name + ", " + this.age + ", " + this.weight +"]";
    }    
}
