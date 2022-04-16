package com.alvachien;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Main2 {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", "Test", 23);
        Person p2 = new Person("Brian", "Test",56);
        Person p3 = new Person("Chelsea","Test",46);
        Person p4 = new Person("David", "Test",28);
        Person p5 = new Person("Erica", "Test",37);
        Person p6 = new Person("Francisco", "Test",18);

        List<Person> people = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6));
        //people.forEach(person -> System.out.println(person));
        people.forEach(System.out::println);

        System.out.println("");
        people.removeIf(person -> person.getAge() < 30);
        people.forEach(System.out::println);

        System.out.println("");
        people.replaceAll(person -> new Person(person.getFirstName(), person.getLastName().toUpperCase(Locale.ROOT), person.getAge()));
        people.forEach(System.out::println);

        System.out.println("");
        people.sort(Comparator.comparing(Person::getAge).reversed());
        people.forEach(System.out::println);
    }
}
