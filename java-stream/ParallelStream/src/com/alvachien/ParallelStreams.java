package com.alvachien;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String[] args) {

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");

        // Version 1. Work
//        Stream.iterate("+", s-> s + "+")
//                .parallel() // => UNORDERED!!!
//                .limit(6)
//                .peek(s -> System.out.println(s + " proceed by the thread " + Thread.currentThread().getName()))
//                .forEach(System.out::println);

        // Version 2. Not working: Parallel for ArrayList.add won't work!!!
//        List<String> strings = new ArrayList<>();
//        Stream.iterate("+", s -> s + "+")
//                .parallel()
//                .limit(1000)
//                //.peek(s -> System.out.println(s + " proceed by  thread " + Thread.currentThread().getName()))
//                .forEach(s -> strings.add(s));
//
//        System.out.println("# " + strings.size());
        // Solution to version 2: using the following line

        // !!!CAUTION!!! Shall not using in productive code => POOR performance
        //List<String> strings = new CopyOnWriteArrayList<>();

        // Version 3: Shall not using in productive code => POOR performance
        List<String> collection =
            Stream.iterate("+", s -> s + "+")
                    .parallel()
                    .limit(1000)
                    //.peek(s -> System.out.println(s + " proceed by  thread " + Thread.currentThread().getName()))
                    //.forEach(s -> strings.add(s));
                    .collect(Collectors.toList());
        System.out.println("# " + collection.size());
    }
}
