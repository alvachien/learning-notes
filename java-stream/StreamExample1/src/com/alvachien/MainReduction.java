package com.alvachien;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class MainReduction {
    public static int reduce(List<Integer> values,
                             int valueIfEmpty,
                             BinaryOperator<Integer> reduction) {
        int result = valueIfEmpty;
        for(int value : values) {
            result = reduction.apply(result, value);
        }
        return result;
    }
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> ints1 = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> ints2 = Arrays.asList(5, 6, 7, 8, 9);

        // Version 1 - Add
        BinaryOperator<Integer> op = (i1, i2) -> i1 + i2;
        System.out.println("Op: i1, i2 -> i1 + i2");

        // Version 1
        int reductionOrigin = reduce(ints, 0, op);
        // Version 2
        int reduction1 = reduce(ints1, 0, op);
        int reduction2 = reduce(ints2, 0, op);
        int reductionNew = reduce(Arrays.asList(reduction1, reduction2), 0, op);

        System.out.println("Reduction Origin: " + reductionOrigin);
        System.out.println("Reduction New: " + reductionNew);

        // Version 2 - Add
        BinaryOperator<Integer> op2 = (i1, i2) -> Integer.max(i1, i2);
        System.out.println("Op: i1, i2 -> Integer.max(i1, i2)");

        reductionOrigin = reduce(ints, 0, op2);
        reduction1 = reduce(ints1, 0, op2);
        reduction2 = reduce(ints2, 0, op2);
        reductionNew = reduce(Arrays.asList(reduction1, reduction2), 0, op2);

        System.out.println("Reduction Origin: " + reductionOrigin);
        System.out.println("Reduction New: " + reductionNew);

        // Version 3 - (i1 + i2) * (i1 + i2)
        BinaryOperator<Integer> op3 = (i1, i2) -> (i1 + i2) * (i1 + i2);
        System.out.println("Op: i1, i2 -> (i1 + i2) * (i1 + i2)");

        reductionOrigin = reduce(ints, 0, op3);
        reduction1 = reduce(ints1, 0, op3);
        reduction2 = reduce(ints2, 0, op3);
        reductionNew = reduce(Arrays.asList(reduction1, reduction2), 0, op3);

        System.out.println("Reduction Origin: " + reductionOrigin);
        System.out.println("Reduction New: " + reductionNew);

        // Version 4 - (i1 + i2) / 2
        BinaryOperator<Integer> op4 = (i1, i2) -> (i1 + i2) / 2;
        System.out.println("Op: i1, i2 -> (i1 + i2) / 2");

        reductionOrigin = reduce(ints, 0, op4);
        reduction1 = reduce(ints1, 0, op4);
        reduction2 = reduce(ints2, 0, op4);
        reductionNew = reduce(Arrays.asList(reduction1, reduction2), 0, op4);

        System.out.println("Reduction Origin: " + reductionOrigin);
        System.out.println("Reduction New: " + reductionNew);
    }
}
