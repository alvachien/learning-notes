package com.alvachien;

public class ArrayTest
{
    public static void main(String[] args) {
        String[] books = new String[]
        {
            "Book 1",
            "Book 2",
            "Book 3",
            "Book 4"
        };

        String[] names = {
            "name1",
            "name2",
            "name3"
        };
        String[] arArr = new String[5];
        System.out.println("Books' length = " + books.length);
        System.out.println("names length = " + names.length);
        System.out.println("arArr length =  " + arArr.length);
        System.out.println();
    }
}
