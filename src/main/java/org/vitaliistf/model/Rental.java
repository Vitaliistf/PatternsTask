package org.vitaliistf.model;

import org.vitaliistf.model.movie.Movie;

import java.io.Serializable;

/**
 * Represents a rental transaction, associating a movie with the number of days it is rented.
 */
public record Rental(Movie movie, int daysRented) implements Serializable {

    /**
     * Calculates the price for the rental based on the movie's price strategy and the number of days rented.
     *
     * @return The calculated price for the rental.
     */
    public double calculatePrice() {
        return movie.getPriceCode().getPriceStrategy().calculatePrice(daysRented);
    }

    /**
     * Calculates the frequent renter points earned for the rental based on the movie's price strategy and the number of days rented.
     *
     * @return The calculated frequent renter points.
     */
    public int calculatePoints() {
        return movie.getPriceCode().getPriceStrategy().calculatePoints(daysRented);
    }
}
