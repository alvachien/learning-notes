package com.alvachien;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainBuildingStreams {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(0, 1, 2, 3, 4);

        System.out.println("Version 1: steam()");
        Stream<Integer> stream = ints.stream();
        stream.forEach(System.out::println);

        System.out.println("Version 2: Stream.of()");
        Stream<Integer> stream2 = Stream.of(0, 1, 3, 4, 5);
        stream2.forEach(System.out::println);

        System.out.println("Version 3: Stream.generate");
        Stream<String> stream3 = Stream.generate(() -> "one");
        stream3.limit(5).forEach(System.out::println);

        System.out.println("Version 4: Stream.iterate");
        Stream<String> stream4 = Stream.iterate("+", s -> s + "+");
        stream4.limit(5).forEach(System.out::println);

        System.out.println("Version 5: ThreadLocalRandom.current().ints");
        IntStream stream5 = ThreadLocalRandom.current().ints();
        stream5.limit(5).forEach(System.out::println);

    }
}
