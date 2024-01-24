package org.vitaliistf.strategy;

/**
 * Implementation of the PriceStrategy interface for calculating rental pricing for regular movies.
 */
public class RegularPriceStrategy implements PriceStrategy {

    /**
     * Calculates the rental price for a regular movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental price.
     */
    @Override
    public double calculatePrice(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

    /**
     * Calculates the rental points for a regular movie based on the number of days rented.
     *
     * @param daysRented The number of days the movie is rented for.
     * @return The calculated rental points.
     */
    @Override
    public int calculatePoints(int daysRented) {
        return 1;
    }
}
