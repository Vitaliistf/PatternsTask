package org.vitaliistf.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vitaliistf.model.movie.Movie;
import org.vitaliistf.model.movie.MovieType;
import org.vitaliistf.serialization.DataManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {

    private MovieController controller;

    @BeforeEach
    void loadController() {
        DataManager dataManager = DataManager.getInstance();
        controller = new MovieController(dataManager);
    }

    @Test
    void testAddMovie() {
        Movie movie = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();

        controller.addMovie(movie);

        assertTrue(controller.getMovieCatalog().contains(movie));
    }

    @Test
    void testFindMovieByTitle() {
        Movie movie = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();

        controller.addMovie(movie);

        assertEquals(Optional.of(movie), controller.findMovieByTitle("Inception"));
        assertEquals(Optional.empty(), controller.findMovieByTitle("Nonexistent Movie"));
    }

    @Test
    void testFindMovieByDirector() {
        Movie movie1 = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();
        Movie movie2 = new Movie.Builder("Interstellar", MovieType.REGULAR)
                .director("Christopher Nolan")
                .actors(List.of("Matthew McConaughey"))
                .build();

        controller.addMovie(movie1);
        controller.addMovie(movie2);

        List<Movie> result = controller.findMovieByDirector("Christopher Nolan");

        assertEquals(2, result.size());
        assertTrue(result.contains(movie1));
        assertTrue(result.contains(movie2));
    }

    @Test
    void testFindMovieByType() {
        Movie movie1 = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();
        Movie movie2 = new Movie.Builder("The Shawshank Redemption", MovieType.REGULAR)
                .director("Frank Darabont")
                .actors(List.of("Tim Robbins"))
                .build();

        controller.addMovie(movie1);
        controller.addMovie(movie2);

        List<Movie> result = controller.findMovieByType(MovieType.REGULAR);

        assertEquals(1, result.size());
        assertTrue(result.contains(movie2));
    }

    @Test
    void testFindMovieByCountry() {
        Movie movie1 = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .countryOfOrigin("USA")
                .build();
        Movie movie2 = new Movie.Builder("Crouching Tiger, Hidden Dragon", MovieType.NEW_RELEASE)
                .director("Ang Lee")
                .actors(List.of("Chow Yun-fat"))
                .countryOfOrigin("China")
                .build();

        controller.addMovie(movie1);
        controller.addMovie(movie2);

        List<Movie> result = controller.findMovieByCountry("China");

        assertEquals(1, result.size());
        assertTrue(result.contains(movie2));
    }

    @Test
    void testFindMovieByActor() {
        Movie movie1 = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio", "Tom Hardy"))
                .build();
        Movie movie2 = new Movie.Builder("The Dark Knight", MovieType.REGULAR)
                .director("Christopher Nolan")
                .actors(List.of("Christian Bale", "Heath Ledger"))
                .build();

        controller.addMovie(movie1);
        controller.addMovie(movie2);

        List<Movie> result = controller.findMovieByActor("Heath Ledger");

        assertEquals(1, result.size());
        assertTrue(result.contains(movie2));
    }

    @Test
    void testDeleteMovie() {
        Movie movie = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();

        controller.addMovie(movie);

        assertTrue(controller.deleteMovie(movie));
        assertFalse(controller.getMovieCatalog().contains(movie));
    }
}
