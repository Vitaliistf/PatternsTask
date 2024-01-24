package org.vitaliistf.strategy;

/**
 * Implementation of the PriceStrategy interface for calculating rental pricing for new release movies.
 */
public class NewReleasePriceStrategy implements PriceStrategy {

    /**
     * Calculates the rental price for a new release movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental price.
     */
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 3;
    }

    /**
     * Calculates the rental points for a new release movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental points.
     */
    @Override
    public int calculatePoints(int daysRented) {
        return 1 + (daysRented > 1 ? 1 : 0);
    }
}
