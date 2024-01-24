package org.vitaliistf.controller;

import org.vitaliistf.model.Customer;
import org.vitaliistf.model.movie.Movie;
import org.vitaliistf.serialization.DataManager;
import org.vitaliistf.view.RentalsView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing customers in the Movie Rental System.
 */
public class CustomerController {

    private final List<Customer> customers;
    private final RentalsView textRentalsView;
    private final RentalsView htmlRentalsView;
    private final DataManager dataManager;

    /**
     * Constructs a new instance of the CustomerController.
     */
    public CustomerController(RentalsView textRentalsView, RentalsView htmlRentalsView, DataManager dataManager) {
        this.customers = new ArrayList<>();
        this.textRentalsView = textRentalsView;
        this.htmlRentalsView = htmlRentalsView;
        this.dataManager = dataManager;
    }

    /**
     * Gets a copy of the customer list.
     *
     * @return The customer list.
     */
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    /**
     * Finds a customer by name.
     *
     * @param name The name of the customer to find.
     * @return The customer object as an Optional if found, or an empty Optional if not found.
     */
    public Optional<Customer> findCustomerByName(String name) {
        return customers.stream()
                .filter(customer -> customer.name().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Adds a customer to the system.
     *
     * @param customer The customer to be added.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Generates a text representation of a customer's rentals.
     *
     * @param customerName The name of the customer.
     * @return Optional containing the text representation, or empty if the customer is not found.
     */
    public Optional<String> getCustomerTextRentalsView(String customerName) {
        Optional<Customer> customer = findCustomerByName(customerName);
        return customer.map(textRentalsView::generateRentalsOutput);
    }

    /**
     * Generates an HTML representation of a customer's rentals and saves it to a file.
     *
     * @param customerName The name of the customer.
     * @return Optional containing a message indicating the result of the operation, or empty if the customer is not found.
     */
    public Optional<String> generateCustomerHtmlRentalsView(String customerName) {
        return findCustomerByName(customerName)
                .map(customer -> {
                    String htmlContent = htmlRentalsView.generateRentalsOutput(customer);
                    String fileName = customerName.toLowerCase() + "_rentals.html";

                    try (PrintWriter writer = new PrintWriter(fileName)) {
                        writer.println(htmlContent);
                        return "HTML file generated: " + fileName;
                    } catch (FileNotFoundException e) {
                        return "Error occurred.\n" + e;
                    }
                });
    }

    /**
     * Adds a rental to the system.
     *
     * @param customer The customer to be added.
     */
    public void addRental(Customer customer, Movie movie, int days) {
        customer.rentMovie(movie, days);
    }

    /**
     * Saves the customer list to a file.
     *
     * @param fileName The name of the file to save the data to.
     */
    public void saveDataToFile(String fileName) {
        dataManager.saveCustomersToFile(fileName, customers);
    }

    /**
     * Loads the customer list from a file.
     *
     * @param fileName The name of the file to load the data from.
     */
    public void loadDataFromFile(String fileName) {
        dataManager.loadCustomersFromFile(fileName, customers);
    }
}
