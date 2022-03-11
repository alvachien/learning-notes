package com.alvachien.learning.java_tutorial.basic_knowledge;

// public sealed class Person permits Student {
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void run() {
        System.out.println("Person.run");
    }

    @Override
    public String toString() {
        return "Person:name=" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;

            return this.name.equals(p.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
