package com.alvachien;

import java.util.function.Function;

public class MainComparator {
    public static void main(String... args) {
        Comparator<Person> cmpAge = (p1, p2) -> p2.getAge() - p1.getAge();
        Comparator<Person> cmpFirstName = (p1, p2) -> p1.getFirstName().compareTo((p2.getFirstName()));
        Comparator<Person> cmpLastName = (p1, p2) -> p1.getLastName().compareTo((p2.getLastName()));

        Function<Person, Integer> f1 = p -> p.getAge();
        Function<Person, String> f2 = p -> p.getLastName();
        Function<Person, String> f3 = p -> p.getFirstName();

        // Version 1.
        // Comparator<Person> cmpPerson = Comparator.comparing(f1);
        // Version 2.
        Comparator<Person> cmpPersonAge = Comparator.comparing(Person::getAge);
        Comparator<Person> cmpPersonLastName = Comparator.comparing(Person::getLastName);

        // Version 1
        Comparator<Person> cmp = cmpPersonAge.thenComparing(cmpPersonLastName);
        // Version 2
        Comparator<Person> cmp2 = Comparator.comparing(Person::getAge)
                .thenComparing(Person::getLastName);
    }
}
