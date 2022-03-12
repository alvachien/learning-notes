package com.alvachien;

public class Person {
    public int age;
    public double height;
    public Person()
    {
    }
    
    public Person(double hgt, int ag)
    {
        this.age = ag;
        this.height = hgt;
    }
    public void printinfo() {
        System.out.println("Age = " + age + ", and Heigth = " + height);
    }
}
