package com.alvachien;

import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(DataWrap[] data) {
        int arrayLength = data.length;
        for(int i = 0; i < arrayLength; i ++) {
            boolean flag = false;

            for(int j = 0; j < arrayLength - 1 -i; j ++) {
                if (data[j].compareTo(data[j+1]) > 0) {
                    DataWrap tmp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = tmp;

                    flag = true;
                }
            }

            System.out.println(Arrays.toString(data));

            if (!flag) {
                break;
            }
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
        bubbleSort(data);
        System.out.println("After: " + Arrays.toString(data));
    }

}
