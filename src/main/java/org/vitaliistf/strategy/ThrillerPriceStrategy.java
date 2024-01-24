package org.vitaliistf.strategy;

/**
 * Implementation of the PriceStrategy interface for calculating rental pricing for thriller movies.
 */
public class ThrillerPriceStrategy implements PriceStrategy {

    /**
     * Calculates the rental price for a thriller movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental price.
     */
    @Override
    public double calculatePrice(int daysRented) {
        return 3 + daysRented * 2.5;
    }

    /**
     * Calculates the rental points for a thriller movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental points.
     */
    @Override
    public int calculatePoints(int daysRented) {
        return 1 + (daysRented < 2 ? 1 : 0);
    }

}
