package org.vitaliistf.strategy;

/**
 * Interface for defining the pricing strategy for different types of movies.
 */
public interface PriceStrategy {

    /**
     * Calculates the rental price based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental price.
     */
    double calculatePrice(int daysRented);

    /**
     * Calculates the rental points based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental points.
     */
    int calculatePoints(int daysRented);
}
