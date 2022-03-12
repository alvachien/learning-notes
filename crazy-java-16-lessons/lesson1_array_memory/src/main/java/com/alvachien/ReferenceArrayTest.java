package com.alvachien;

public class ReferenceArrayTest {

    public static void main(String[] args) {
        Person[] person;

        person = new Person[2];
        System.out.println("Current length = " + person.length);
        System.out.println(person);

        // Create first person
        Person p1 = new Person();
        p1.age = 11;
        p1.height = 112;
        Person p2 = new Person();
        p2.age = 16;
        p2.height = 134;
        person[0] = p1;
        person[1] = p2;
        System.out.println("After insert");
        System.out.println(person);
        for(int i = 0; i < person.length; i ++) {
            person[i].printinfo();
        }
    }
}
