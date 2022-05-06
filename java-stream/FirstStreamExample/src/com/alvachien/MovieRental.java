package com.alvachien;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRental {
    private List<Rental> rentals = new ArrayList<>();

    public void addRental(String name, int daysRented) {
        Rental rental = new Rental(name, daysRented);
        this.rentals.add(rental);
    }

    public String statement() {
        // Version 1.
//        double totalAmount = 0;
//        for(Rental rental: rentals) {
//            totalAmount += computeRentalAmount(rental);
//        }
//        int frequentRenterPoints = 0;
//        for(Rental rental: rentals) {
//            frequentRenterPoints += getFrequentRenterPoints(rental);
//        }
//        String statement = composeHeader();
//        for(Rental rental: rentals) {
//            statement += computeStatementLine(rental);
//        }

        // Version 2
        double totalAmount = rentals.stream()
                .mapToDouble(this::computeRentalAmount)
                .sum();
        int frequentRenterPoints = rentals.stream()
                .mapToInt(this::getFrequentRenterPoints)
                .sum();

        String statement = composeHeader();
        statement += rentals.stream()
                .map(this::computeStatementLine)
                .collect(Collectors.joining());
        statement += composeFooter(totalAmount, frequentRenterPoints);

        return statement;
    }

    public String composeHeader() {
        return "Statement for the rental of " + rentals.size() + " movies\n";
    }
    public double computeRentalAmount(Rental rental) {
        return 2.0 + rental.getDaysRented();
    }

    public int getFrequentRenterPoints(Rental rental) {
        return rental.getDaysRented() > 2? 2 : 1;
    }

    public String computeStatementLine(Rental rental) {
        return "\t" + rental.getName() + ": " + computeRentalAmount(rental) + "\n";
    }

    public String composeFooter(double totalAmount, int frequentRenterPoints) {
        return "Total amount owed: " + totalAmount +
                "\nFrequent renter points earned: " + frequentRenterPoints;
    }
}
