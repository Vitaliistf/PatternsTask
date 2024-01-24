package org.vitaliistf.serialization;

import org.junit.jupiter.api.Test;
import org.vitaliistf.model.Customer;
import org.vitaliistf.model.movie.Movie;
import org.vitaliistf.model.movie.MovieType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataManagerTest {

    @Test
    void testSaveAndLoadDataToFile() {
        DataManager dataManager = DataManager.getInstance();

        Movie movie = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();

        Customer customer = new Customer("John Doe", new ArrayList<>());

        List<Movie> movies = new ArrayList<>(List.of(movie));
        List<Customer> customers = new ArrayList<>(List.of(customer));
        customer.rentMovie(movie, 3);

        String customerFileName = "test_customer_data.txt";
        String movieFileName = "test_movie_data.txt";

        dataManager.saveCustomersToFile(customerFileName, customers);
        dataManager.saveMovieCatalogToFile(movieFileName, movies);

        // Clear existing data
        movies.clear();
        customers.clear();

        dataManager.loadCustomersFromFile(customerFileName, customers);
        dataManager.loadMovieCatalogFromFile(movieFileName, movies);

        // Check if data was loaded successfully
        assertTrue(movies.contains(movie));
        assertTrue(customers.contains(customer));
    }
}
