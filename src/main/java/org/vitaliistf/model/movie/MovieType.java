package org.vitaliistf.model.movie;

import org.vitaliistf.strategy.*;

/**
 * Enumeration representing different types of movies along with their corresponding price strategies.
 */
public enum MovieType {
    REGULAR(new RegularPriceStrategy()),
    NEW_RELEASE(new NewReleasePriceStrategy()),
    CHILDRENS(new ChildrensPriceStrategy()),
    DRAMA(new DramaPriceStrategy()),
    COMEDY(new ComedyPriceStrategy()),
    THRILLER(new ThrillerPriceStrategy());

    private final PriceStrategy priceStrategy;

    /**
     * Constructor for MovieType enum.
     *
     * @param priceStrategy The PriceStrategy associated with the movie type.
     */
    MovieType(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    /**
     * Get the PriceStrategy associated with the movie type.
     *
     * @return The PriceStrategy instance.
     */
    public PriceStrategy getPriceStrategy() {
        return priceStrategy;
    }
}
