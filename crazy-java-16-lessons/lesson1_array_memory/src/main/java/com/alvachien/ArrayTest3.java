package com.alvachien;

public class ArrayTest3 
{
    public static void main(String[] args) {
        int[] nums = new int[]{ 3, 5, 20, 12 };
        int[] prices;

        prices = nums;
        for(int i = 0; i < prices.length; i++) 
        {
            System.out.println(prices[i]);
        }

        // Change the third element 
        prices[2] = 34;
        for(int i = 0; i < prices.length; i++) 
        {
            System.out.println(prices[i]);
        }
    }
}
