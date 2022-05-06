package com.alvachien;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
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

        // Stream of Regular expression
        System.out.println("---------------------");
        System.out.println("Stream for array");
        Person[] arpeople = {p01, p02, p03, p04, p05, p06, p07, p08, p09};
        Stream<Person> peopleStream1 = Arrays.stream(arpeople);
        Stream<Person> peopleStream2 = Stream.of(arpeople);
        peopleStream1.forEach(System.out::println);
        peopleStream2.forEach(System.out::println);

        // Stream from text file
        System.out.println("---------------------");
        System.out.println("Stream for text file");
        Path path = Path.of("data/first-names.txt");
        try (Stream<String> lines = Files.lines(path)) {
               long count = lines.count();
               System.out.println("# Line in file: " + count);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        // Stream from Regular expression
        System.out.println("---------------------");
        System.out.println("Stream by String.split => string[]");
        String sentence = "the quick brown fox jumps over the lazy dog";
        String[] words = sentence.split(" ");
        Stream<String> wordsStream = Arrays.stream(words);
        long countOfWord = wordsStream.count();
        System.out.println("# words of the sentence: " + countOfWord);

        System.out.println("---------------------");
        System.out.println("Stream by Patter.compile and spliteAsStream");
        Pattern pattern = Pattern.compile(" ");
        long countOfWord2 = pattern.splitAsStream(sentence).count();
        System.out.println("# words of the sentence 2 (better performance): " + countOfWord2);

        // Stream from string
        System.out.println("---------------------");
        System.out.println("Stream by String.chars");
        sentence.chars()
                .mapToObj(codepoint -> Character.toString((codepoint)))
                .filter(letter -> !letter.equals(" "))
                .distinct()
                .sorted()
                .forEach(System.out::print);

        // Stream from string
        System.out.println("---------------------");
        System.out.println("Stream by IntStream.range");

        IntStream.range(0, 30)
                .skip(10)
                .limit(10)
                .forEach(System.out::print);
        System.out.println();

        // Stream from Stream.iterate
        System.out.println("---------------------");
        System.out.println("Stream by Stream.iterate && takeWhile");
        Class<?> clzz = ArrayList.class;
        clzz.getSuperclass();

        Stream.<Class<?>>iterate(clzz, c -> c.getSuperclass())
                .takeWhile(c -> c != null)
                .forEach(System.out::println);

        // Optional for average
        System.out.println("---------------------");
        System.out.println("Stream to replace loop");
        double average = people.stream()
                .mapToInt(p -> p.getAge())
                .filter(age -> age > 20)
                .average()
                .orElseThrow();
        System.out.println("Average = " + average);

        // Movie rental
        System.out.println("---------------------");
        System.out.println("Movie rental example");
        MovieRental movieRental = new MovieRental();
        movieRental.addRental("Blade Runner", 2);
        movieRental.addRental("Frozen", 3);
        movieRental.addRental("Star Wars", 1);

        String statement = movieRental.statement();
        System.out.println(statement);
    }
}
