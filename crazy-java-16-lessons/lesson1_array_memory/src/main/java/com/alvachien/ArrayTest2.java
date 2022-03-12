package com.alvachien;

public class ArrayTest2 {
    public static void main(String[] args) {
        int[] pos = new int[5];
        for(int i = 0; i < pos.length; i ++) {
            pos[i] = (i + 1) * 2;
        }

        int a = pos[1];
        int b = 20;
        pos[2] = b;

        Person[] persons = new Person[2];
        persons[0] = new Person(3.34, 2);
        persons[1] = new Person(3.2, 2);

        for(int i = 0; i < persons.length; i ++) {
            persons[i].printinfo();
        }

        Person p1 = persons[0];
        Person p2 = new Person(4.3, 3);
        persons[1] = p2;
        for(int i = 0; i < persons.length; i ++) {
            persons[i].printinfo();
        }
    }
}
