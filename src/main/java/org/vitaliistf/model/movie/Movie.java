package org.vitaliistf.model.movie;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Represents a movie with details such as title, price code, country of origin, description, director, and actors.
 */
public class Movie implements Serializable {
    private final String title;
    private final MovieType priceCode;
    private final String countryOfOrigin;
    private final String description;
    private final String director;
    private final List<String> actors;

    /**
     * Private constructor to create a Movie instance using a Builder.
     *
     * @param builder The builder object containing movie details.
     */
    private Movie(Builder builder) {
        this.title = builder.title;
        this.priceCode = builder.priceCode;
        this.countryOfOrigin = builder.countryOfOrigin;
        this.description = builder.description;
        this.director = builder.director;
        this.actors = builder.actors;
    }

    /**
     * Get the price code of the movie.
     *
     * @return The MovieType representing the price code.
     */
    public MovieType getPriceCode() {
        return priceCode;
    }

    /**
     * Get the title of the movie.
     *
     * @return The title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the country of origin of the movie.
     *
     * @return The country of origin of the movie.
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Get the description of the movie.
     *
     * @return The description of the movie.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the director of the movie.
     *
     * @return The director of the movie.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Get the list of actors in the movie.
     *
     * @return The list of actors in the movie.
     */
    public List<String> getActors() {
        return actors;
    }

    /**
     * Override toString method to provide a string representation of the movie.
     *
     * @return A formatted string containing movie details.
     */
    @Override
    public String toString() {
        return "\n" + "Title: " + title + "\n" +
                "Director: " + director + "\n" +
                "Price Code: " + priceCode + "\n" +
                "Country of Origin: " + countryOfOrigin + "\n" +
                "Description: " + description + "\n" +
                "Actors: " + String.join(", ", actors) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                priceCode == movie.priceCode &&
                Objects.equals(countryOfOrigin, movie.countryOfOrigin) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(actors, movie.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, priceCode, countryOfOrigin, description, director, actors);
    }

    /**
     * Builder class for constructing a Movie object.
     */
    public static class Builder {
        private String title;
        private MovieType priceCode;
        private String countryOfOrigin = "";
        private String description = "";
        private String director = "";
        private List<String> actors;

        /**
         * Constructor for the Builder class.
         *
         * @param title    The title of the movie.
         * @param priceCode The price code of the movie (default is REGULAR).
         */
        public Builder(String title, MovieType priceCode) {
            this.title = title;
            this.priceCode = priceCode;
        }

        /**
         * Constructor for the Builder class with default price code (REGULAR).
         *
         * @param title The title of the movie.
         */
        public Builder(String title) {
            this.title = title;
            this.priceCode = MovieType.REGULAR;
        }

        /**
         * Set the title of the movie.
         *
         * @param title The title of the movie.
         * @return The Builder instance for method chaining.
         */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set the price code of the movie.
         *
         * @param priceCode The price code of the movie.
         * @return The Builder instance for method chaining.
         */
        public Builder priceCode(MovieType priceCode) {
            this.priceCode = priceCode;
            return this;
        }

        /**
         * Set the country of origin of the movie.
         *
         * @param countryOfOrigin The country of origin of the movie.
         * @return The Builder instance for method chaining.
         */
        public Builder countryOfOrigin(String countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
            return this;
        }

        /**
         * Set the description of the movie.
         *
         * @param description The description of the movie.
         * @return The Builder instance for method chaining.
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Set the director of the movie.
         *
         * @param director The director of the movie.
         * @return The Builder instance for method chaining.
         */
        public Builder director(String director) {
            this.director = director;
            return this;
        }

        /**
         * Set the list of actors in the movie.
         *
         * @param actors The list of actors in the movie.
         * @return The Builder instance for method chaining.
         */
        public Builder actors(List<String> actors) {
            this.actors = actors;
            return this;
        }

        /**
         * Build and return the Movie object.
         *
         * @return The constructed Movie object.
         */
        public Movie build() {
            return new Movie(this);
        }
    }
}
