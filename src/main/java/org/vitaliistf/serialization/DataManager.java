package org.vitaliistf.serialization;

import org.vitaliistf.model.Customer;
import org.vitaliistf.model.movie.Movie;

import java.io.*;
import java.util.List;

/**
 * Manages the storage and retrieval of data for the Movie Rental System.
 */
public class DataManager {

    /**
     * Singleton instance of DataManager.
     */
    private static DataManager instance;

    /**
     * Private constructor to make object producible only inside the class.
     */
    private DataManager() {
    }

    /**
     * Method to get singleton instance of DataManager.
     *
     * @return DataManager instance.
     */
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    /**
     * Saves the customer list to a file.
     *
     * @param fileName      The name of the file to save the data to.
     * @param customers     The list of customers to be saved.
     */
    public void saveCustomersToFile(String fileName, List<Customer> customers) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the movie catalog to a file.
     *
     * @param fileName      The name of the file to save the data to.
     * @param movieCatalog  The movie catalog to be saved.
     */
    public void saveMovieCatalogToFile(String fileName, List<Movie> movieCatalog) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(movieCatalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the movie catalog from a file.
     *
     * @param fileName      The name of the file to load the data from.
     * @param movieCatalog  The movie catalog to be loaded.
     */
    @SuppressWarnings("unchecked")
    public void loadMovieCatalogFromFile(String fileName, List<Movie> movieCatalog) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            movieCatalog.clear();

            List<Movie> loadedMovies = (List<Movie>) objectInputStream.readObject();

            movieCatalog.addAll(loadedMovies);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the customer list from a file.
     *
     * @param fileName      The name of the file to load the data from.
     * @param customers     The list of customers to be loaded.
     */
    @SuppressWarnings("unchecked")
    public void loadCustomersFromFile(String fileName, List<Customer> customers) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            customers.clear();

            List<Customer> loadedCustomers = (List<Customer>) objectInputStream.readObject();

            customers.addAll(loadedCustomers);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
