package com.alvachien;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUsingOptional {
    public static void main(String ... args) {

        // Version 1: Cannot support parallel
//        List<Double> result = new ArrayList<>();
//        ThreadLocalRandom.current()
//                .doubles(10_000)
//                .boxed()
//                // .parallel() // => Losting elements
//                .forEach(
//                        d -> NewMath.inv(d)
//                                .ifPresent(
//                                        inv -> NewMath.sqrt(inv)
//                                                .ifPresent(sqrt -> result.add(sqrt))
//                                )
//                );
//
//        System.out.println("# result = " + result.size());

        // Version 2: Correct one
        Function<Double, Stream<Double>> flatMapper =
                d -> NewMath.inv(d)
                        .flatMap(inv -> NewMath.sqrt(inv))
                        .map(sqrt -> Stream.of(sqrt))
                        .orElseGet(() -> Stream.empty());

        List<Double> result2 = ThreadLocalRandom.current()
                .doubles(10_000)
                .map(d -> d * 20 - 10) // generate some negative ones
                .boxed()
                .flatMap(flatMapper)
                .collect(Collectors.toList());

        System.out.println("# right result = " + result2.size());
    }
}
