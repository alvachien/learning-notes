package com.alvachien;

public class Rental {
    private String name;
    private int daysRented;

    public Rental(String name, int daysRented) {
        this.name = name;
        this.daysRented = daysRented;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }
}
