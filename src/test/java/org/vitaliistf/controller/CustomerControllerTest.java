package org.vitaliistf.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vitaliistf.model.Customer;
import org.vitaliistf.model.movie.Movie;
import org.vitaliistf.model.movie.MovieType;
import org.vitaliistf.serialization.DataManager;
import org.vitaliistf.view.HtmlRentalsView;
import org.vitaliistf.view.TextRentalsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerControllerTest {

    private CustomerController controller;

    @BeforeEach
    void loadController() {
        TextRentalsView textRentalsView = TextRentalsView.getInstance();
        HtmlRentalsView htmlRentalsView = HtmlRentalsView.getInstance();
        DataManager dataManager = DataManager.getInstance();
        controller = new CustomerController(textRentalsView, htmlRentalsView, dataManager);
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer("John Doe", new ArrayList<>());

        controller.addCustomer(customer);

        assertTrue(controller.getCustomers().contains(customer));
    }

    @Test
    void testAddRental() {
        Customer customer = new Customer("John Doe", new ArrayList<>());
        Movie movie = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();

        controller.addCustomer(customer);
        controller.addRental(customer, movie, 3);

        assertTrue(customer.rentals().size() > 0);
    }

    @Test
    void testFindCustomerByName() {
        Customer customer = new Customer("John Doe", new ArrayList<>());

        controller.addCustomer(customer);

        assertEquals(Optional.of(customer), controller.findCustomerByName("John Doe"));
        assertEquals(Optional.empty(), controller.findCustomerByName("Nonexistent Customer"));
    }

    @Test
    void testGenerateCustomerHtmlRentalsView() {
        Customer customer = new Customer("John Doe", new ArrayList<>());
        Movie movie = new Movie.Builder("Inception", MovieType.NEW_RELEASE)
                .director("Christopher Nolan")
                .actors(List.of("Leonardo DiCaprio"))
                .build();

        controller.addCustomer(customer);
        controller.addRental(customer, movie, 3);

        Optional<String> result = controller.generateCustomerHtmlRentalsView("John Doe");

        assertTrue(result.isPresent());
        assertTrue(result.get().contains("HTML file generated"));
    }


}
