package org.vitaliistf.controller;

import org.vitaliistf.model.movie.MovieType;
import org.vitaliistf.serialization.DataManager;
import org.vitaliistf.model.movie.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing movies in the Movie Rental System.
 */
public class MovieController {

    private final List<Movie> movieCatalog;
    private final DataManager dataManager;

    /**
     * Constructs a new instance of the MovieRentalController.
     */
    public MovieController(DataManager dataManager) {
        this.movieCatalog = new ArrayList<>();
        this.dataManager = dataManager;
    }

    /**
     * Gets a copy of the movie catalog.
     *
     * @return The movie catalog.
     */
    public List<Movie> getMovieCatalog() {
        return new ArrayList<>(movieCatalog);
    }

    /**
     * Adds a movie to the movie catalog.
     *
     * @param movie The movie to be added.
     */
    public void addMovie(Movie movie) {
        movieCatalog.add(movie);
    }

    /**
     * Finds a movie in the movie catalog by its title.
     *
     * @param title The title of the movie to find.
     * @return Optional containing the movie, or empty if not found.
     */
    public Optional<Movie> findMovieByTitle(String title) {
        return movieCatalog.stream()
                .filter(movie -> title.equalsIgnoreCase(movie.getTitle()))
                .findFirst();
    }

    /**
     * Searches for movies directed by a specific director.
     *
     * @param director The director's name to search for.
     * @return List of movies directed by the specified director.
     */
    public List<Movie> findMovieByDirector(String director) {
        return movieCatalog.stream()
                .filter(movie -> movie.getDirector().equals(director))
                .toList();
    }

    /**
     * Finds movies in the movie catalog by its type.
     *
     * @param movieType The type of the movie.
     * @return List of the movies, or empty list if not found.
     */
    public List<Movie> findMovieByType(MovieType movieType) {
        return movieCatalog.stream()
                .filter(movie -> movie.getPriceCode().equals(movieType))
                .toList();

    }

    /**
     * Finds movies in the movie catalog by its country of origin.
     *
     * @param country The country of the movie.
     * @return List of the movies, or empty list if not found.
     */
    public List<Movie> findMovieByCountry(String country) {
        return movieCatalog.stream()
                .filter(movie -> movie.getCountryOfOrigin().equalsIgnoreCase(country))
                .toList();
    }

    /**
     * Finds movies in the movie catalog by actor.
     *
     * @param actor The actor from movie.
     * @return List of the movies, or empty list if not found.
     */
    public List<Movie> findMovieByActor(String actor) {
        return movieCatalog.stream()
                .filter(movie -> movie.getActors().contains(actor))
                .toList();
    }

    /**
     * Removes movie from the catalog.
     *
     * @param movie The movie.
     * @return true if movie was deleted, false otherwise.
     */
    public boolean deleteMovie(Movie movie) {
        return movieCatalog.remove(movie);
    }

    /**
     * Saves the movie catalog to a file.
     *
     * @param fileName The name of the file to save the data to.
     */
    public void saveDataToFile(String fileName) {
        dataManager.saveMovieCatalogToFile(fileName, movieCatalog);
    }

    /**
     * Loads the movie catalog from a file.
     *
     * @param fileName The name of the file to load the data from.
     */
    public void loadDataFromFile(String fileName) {
        dataManager.loadMovieCatalogFromFile(fileName, movieCatalog);
    }
}
