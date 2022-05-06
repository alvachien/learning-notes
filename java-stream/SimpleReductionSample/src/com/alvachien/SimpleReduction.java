package com.alvachien;

import java.util.List;
import java.util.Optional;

public class SimpleReduction {
    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 1, 1, 1, 1);

        // Version 1
        System.out.println("=============================================");
        System.out.println("Version 1: Using reduce(function): ");
        Optional<Integer> reduce = ints.stream().reduce((i1, i2) -> i1 + i2);

        System.out.println("reduce: " + reduce);
        // Java 8
        System.out.println("Java 8 using get() on Optional: " + reduce.get());
        // reduce.get();
        // Recommend by Java 10
        System.out.println("Java 10 using orElseThrow() on Optional: ");
        Integer sum = reduce.orElseThrow();
        System.out.println("sum = " + sum);

        // Version 2
        System.out.println("=============================================");
        System.out.println("Version 2: Using reduce(Identity, function): ");
        int sum2 = ints.stream().reduce(0, (i1, i2) -> i1 + i2);
        System.out.println("Sum2 = " + sum2);
        System.out.println("If the identity is wrong, the result is wrong: ");
        sum2 = ints.stream().reduce(10, (i1, i2) -> i1 + i2);
        System.out.println("Sum2 (use identity = 10) = " + sum2);
    }
}
