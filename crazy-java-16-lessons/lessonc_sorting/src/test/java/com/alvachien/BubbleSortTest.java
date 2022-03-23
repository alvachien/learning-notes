package com.alvachien;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BubbleSortTest {
    @Test
    void test() {
        DataWrap[] data = {
            new DataWrap(21, ""),
            new DataWrap(30, ""),
            new DataWrap(49, ""),
            new DataWrap(30, "*"),
            new DataWrap(16, ""),
            new DataWrap(9, "")
        };

        System.out.println("Before BubbleSort: " + Arrays.toString(data));
        BubbleSort.bubbleSort(data);
        System.out.println("After BubbleSort: " + Arrays.toString(data));

    }
}
