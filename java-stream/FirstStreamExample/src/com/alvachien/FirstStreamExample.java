package com.alvachien;

import java.util.List;
import java.util.stream.Stream;

public class FirstStreamExample {
    public static void main(String[] args) {
        Person p01 = new Person("Paul", 25);
        Person p02 = new Person("Sarah", 27);
        Person p03 = new Person("James", 31);
        Person p04 = new Person("Julie", 25);
        Person p05 = new Person("Charles", 22);
        Person p06 = new Person("Charlotte", 31);
        Person p07 = new Person("Ann", 27);
        Person p08 = new Person("Boris", 29);
        Person p09 = new Person("Emily", 34);

        List<Person> people = List.of(p01, p02, p03, p04, p05, p06, p07, p08, p09);
        long countOfAge30 = people.stream()
                .map(p -> p.getAge()).filter(p -> p > 30)
                .count();

        System.out.println("# people who age > 30: " + countOfAge30);

        City newYork = new City("New York", p01, p02, p03);
        City paris = new City("Paris", p04, p05, p06);
        City london = new City("London", p07, p08, p09);

        List<City> cities = List.of(newYork, paris, london);

        long countOfPeopleInCities = cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .count();
        System.out.println("# people in cities : " + countOfPeopleInCities);

        cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .forEach(System.out::println);
    }
}
