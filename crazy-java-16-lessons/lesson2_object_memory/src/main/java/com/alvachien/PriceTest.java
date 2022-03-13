package com.alvachien;

public class PriceTest {
    public static void main(String[] args){
        System.out.println(Price.INSTANCE.currentPrice);

        Price p = new Price(2.9);
        System.out.println(p.currentPrice);
    }
}
