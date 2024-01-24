package org.vitaliistf.strategy;

/**
 * Implementation of the PriceStrategy interface for calculating rental pricing for drama movies.
 */
public class DramaPriceStrategy implements PriceStrategy {

    /**
     * Calculates the rental price for a drama movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental price.
     */
    @Override
    public double calculatePrice(int daysRented) {
        return 2.5 + daysRented * 1.5;
    }

    /**
     * Calculates the rental points for a drama movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental points.
     */
    @Override
    public int calculatePoints(int daysRented) {
        return 1 + (daysRented > 1 ? 1 : 0);
    }

}
