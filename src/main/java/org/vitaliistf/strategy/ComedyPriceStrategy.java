package org.vitaliistf.strategy;

/**
 * Implementation of the PriceStrategy interface for calculating rental pricing for comedy movies.
 */
public class ComedyPriceStrategy implements PriceStrategy {

    /**
     * Calculates the rental price for a comedy movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental price.
     */
    @Override
    public double calculatePrice(int daysRented) {
        return 2 + daysRented * 2;
    }

    /**
     * Calculates the rental points for a comedy movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental points.
     */
    @Override
    public int calculatePoints(int daysRented) {
        return 1;
    }
}
