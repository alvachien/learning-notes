package com.alvachien;

import java.util.Arrays;

public class SelectSort {
    public static void selectSort(DataWrap[] data) {

        int arrayLength = data.length;
        for(int i = 0; i < arrayLength; i ++) {

            // Solution 1. find out the minimum element
            // for(int j = i + 1; j < arrayLength; j ++) {
            //     if (data[i].compareTo(data[j]) > 0) {
            //         DataWrap tmp = data[i];
            //         data[i] = data[j];
            //         data[j] = tmp;
            //     }
            // }

            // Solution 2. Just figure out the minium index and swapt it later on
            int minIndex = i;
            for(int j = i + 1; j < arrayLength; j ++) {
                if (data[i].compareTo(data[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                DataWrap tmp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = tmp;
            }

            System.out.println(java.util.Arrays.toString(data));
        }
    }

    public static void main(String[] args) {
        DataWrap[] data = {
            new DataWrap(21, ""),
            new DataWrap(30, ""),
            new DataWrap(49, ""),
            new DataWrap(30, "*"),
            new DataWrap(16, ""),
            new DataWrap(9, "")
        };

        System.out.println("Before: " + Arrays.toString(data));
        selectSort(data);
        System.out.println("After: " + Arrays.toString(data));
    }
}
