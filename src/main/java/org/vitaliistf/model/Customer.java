package org.vitaliistf.model;

import org.vitaliistf.model.movie.Movie;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a customer with rental information.
 * The customer name and a list of rentals are stored.
 */
public record Customer(String name, List<Rental> rentals) implements Serializable {

    /**
     * Calculates the total amount owed by the customer for all rentals.
     *
     * @return The total amount owed.
     */
    public double getTotalAmount() {
        return rentals.stream()
                .mapToDouble(Rental::calculatePrice)
                .sum();
    }

    /**
     * Calculates the total frequent renter points earned by the customer for all rentals.
     *
     * @return The total frequent renter points.
     */
    public int getPoints() {
        return rentals.stream()
                .mapToInt(Rental::calculatePoints)
                .sum();
    }

    /**
     * Adds movie rental for customer rentals.
     *
     * @param movie Movie to rent.
     * @param days  Duration of rent.
     */
    public void rentMovie(Movie movie, int days) {
        rentals.add(new Rental(movie, days));
    }

}
