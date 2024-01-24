package org.vitaliistf;

import org.vitaliistf.controller.CustomerController;
import org.vitaliistf.controller.MovieController;
import org.vitaliistf.model.Customer;
import org.vitaliistf.model.Rental;
import org.vitaliistf.model.movie.Movie;
import org.vitaliistf.model.movie.MovieType;
import org.vitaliistf.serialization.DataManager;
import org.vitaliistf.view.HtmlRentalsView;
import org.vitaliistf.view.TextRentalsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The main class for the Movie Rental System.
 * Manages the user interface and interacts with the MovieRentalController.
 */
public class Main {

    private static final String CATALOG_FILENAME = "catalog.txt";
    private static final String CUSTOMERS_FILENAME = "customers.txt";
    private final Scanner scanner;
    private final MovieController movieController;
    private final CustomerController customerController;

    /**
     * Constructs a new instance of the Main class.
     *
     * @param scanner    The Scanner for user input.
     * @param movieController The MovieRentalController for managing movie rentals.
     */
    public Main(Scanner scanner, MovieController movieController, CustomerController customerController) {
        this.scanner = scanner;
        this.customerController = customerController;
        this.movieController = movieController;
    }

    /**
     * The entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        TextRentalsView textRentalsView = TextRentalsView.getInstance();
        HtmlRentalsView htmlRentalsView = HtmlRentalsView.getInstance();
        DataManager dataManager = DataManager.getInstance();
        Main main = new Main(
                new Scanner(System.in),
                new MovieController(dataManager),
                new CustomerController(textRentalsView, htmlRentalsView, dataManager)
        );
        main.run();
    }

    /**
     * Runs the main loop of the Movie Rental System.
     */
    private void run() {
        String choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.next();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" -> displayMovieCatalog();
                case "2" -> addMovie();
                case "3" -> searchMoviesByDirector();
                case "4" -> deleteMovie();
                case "5" -> addMovieRental();
                case "6" -> displayCustomerList();
                case "7" -> addCustomer();
                case "8" -> displayCustomerRentals();
                case "9" -> generateCustomerRentalsHtml();
                case "10" -> saveDataToFile();
                case "11" -> loadDataFromFile();
                case "0" -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("0"));
    }

    /**
     * Displays the main menu of the Movie Rental System.
     */
    private void displayMenu() {
        System.out.println("""
                \n-------------------------
                Movie Rental System Menu:
                1. Display Movie Catalog
                2. Add a Movie to Catalog
                3. Search Movies by Director
                4. Delete movie
                5. Add movie rental
                6. Display Customer List
                7. Add a Customer
                8. Display Customer Rentals
                9. Generate Customer Rentals HTML
                10. Save Data to File
                11. Load Data from File
                0. Exit""");
    }

    /**
     * Displays the Movie Catalog.
     */
    private void displayMovieCatalog() {
        List<Movie> movieCatalog = movieController.getMovieCatalog();
        System.out.println("Movie Catalog:");
        for (Movie movie : movieCatalog) {
            System.out.println(movie + "\n");
        }
    }

    /**
     * Adds a movie to the Movie Catalog.
     */
    private void addMovie() {
        System.out.println("Enter movie details (If there is any detail that cannot be filled, except for title, just press ENTER):");

        System.out.print("Title: ");
        String title = scanner.nextLine();
        while (movieController.findMovieByTitle(title).isPresent()) {
            System.out.println("This movie already exists in catalog. Try another one.");
            title = scanner.nextLine();
        }
        Movie.Builder builder = new Movie.Builder(title);

        System.out.print("Price Code (REGULAR, NEW_RELEASE, CHILDRENS, DRAMA, COMEDY, THRILLER): ");
        String priceCodeInput = scanner.nextLine().toUpperCase();
        MovieType priceCode = isValidMovieType(priceCodeInput) ? MovieType.valueOf(priceCodeInput) : MovieType.REGULAR;
        builder.priceCode(priceCode);

        System.out.print("Country of Origin: ");
        String countryOfOrigin = scanner.nextLine();
        if (!countryOfOrigin.isBlank()) {
            builder.countryOfOrigin(countryOfOrigin);
        }

        System.out.print("Description: ");
        String description = scanner.nextLine();
        if (!description.isBlank()) {
            builder.description(description);
        }

        System.out.print("Director: ");
        String director = scanner.nextLine();
        if (!director.isBlank()) {
            builder.director(director);
        }

        System.out.print("Enter number of actors: ");
        int numActors = scanner.nextInt();
        scanner.nextLine();

        List<String> actors = new ArrayList<>();
        for (int i = 0; i < numActors; i++) {
            System.out.print("Enter actor " + (i + 1) + ": ");
            actors.add(scanner.nextLine());
        }

        Movie movie = builder
                .actors(actors)
                .build();

        movieController.addMovie(movie);
        System.out.println("Movie added to catalog: " + movie.getTitle());
    }

    /**
     * Searches and displays movies by a specific director.
     */
    private void searchMoviesByDirector() {
        System.out.print("Enter director name: ");
        String director = scanner.nextLine();
        List<Movie> movies = movieController.findMovieByDirector(director);

        System.out.println("Movies directed by " + director + ":");
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
        }
    }

    /**
     * Searches and displays movies by a specific director.
     */
    private void deleteMovie() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        Optional<Movie> movie = movieController.findMovieByTitle(title);

        if (movie.isPresent()) {
            movieController.deleteMovie(movie.get());
            System.out.println("Movie is deleted successfully.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    /**
     * Displays the list of customers.
     */
    private void displayCustomerList() {
        List<Customer> customers = customerController.getCustomers();
        System.out.println("Customer List:");
        for (Customer customer : customers) {
            System.out.println(customer.name());
        }
    }

    /**
     * Adds a customer to the system.
     */
    private void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of movies rented: ");
        int numMovies = scanner.nextInt();
        scanner.nextLine();

        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < numMovies; i++) {
            System.out.print("Enter movie title for rental " + (i + 1) + ": ");
            String movieTitle = scanner.nextLine();
            Optional<Movie> movie = movieController.findMovieByTitle(movieTitle);
            if (movie.isPresent()) {
                System.out.print("Enter number of days rented for " + movieTitle + ": ");
                int daysRented = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                rentals.add(new Rental(movie.get(), daysRented));
            } else {
                System.out.println("Movie not found in catalog.");
                i--; // Retry for the same rental
            }
        }

        Customer customer = new Customer(name, rentals);
        customerController.addCustomer(customer);
        System.out.println("Customer added: " + customer.name());
    }

    /**
     * Displays the rental information for a specific customer.
     */
    private void displayCustomerRentals() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        Optional<String> rentals = customerController.getCustomerTextRentalsView(customerName);

        if (rentals.isEmpty()) {
            System.out.println("User not found.");
        } else {
            System.out.println(rentals.get());
        }
    }

    /**
     * Displays the rental information for a specific customer.
     */
    private void addMovieRental() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        Optional<Customer> customer = customerController.findCustomerByName(customerName);

        if (customer.isEmpty()) {
            System.out.println("User not found.");
        } else {
            System.out.print("Enter movie title: ");
            String title = scanner.nextLine();
            Optional<Movie> movie = movieController.findMovieByTitle(title);
            if (movie.isPresent()) {
                System.out.print("Enter duration of rental: ");
                int days = scanner.nextInt();
                customerController.addRental(customer.get(), movie.get(), days);
                System.out.println("Rental is added successfully.");
            }
        }
    }

    /**
     * Generates and displays HTML rental information for a specific customer.
     */
    private void generateCustomerRentalsHtml() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        Optional<String> result = customerController.generateCustomerHtmlRentalsView(customerName);

        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println("Customer not found.");
        }
    }

    /**
     * Saves data to files.
     */
    private void saveDataToFile() {
        customerController.saveDataToFile(CUSTOMERS_FILENAME);
        movieController.saveDataToFile(CATALOG_FILENAME);
        System.out.println("Data saved to files: " + CATALOG_FILENAME + ", " + CUSTOMERS_FILENAME);
    }

    /**
     * Loads data from files.
     */
    private void loadDataFromFile() {
        customerController.loadDataFromFile(CUSTOMERS_FILENAME);
        movieController.loadDataFromFile(CATALOG_FILENAME);
        System.out.println("Data loaded from files: " + CATALOG_FILENAME + ", " + CUSTOMERS_FILENAME);
    }

    /**
     * Checks if the entered movie type is valid.
     *
     * @param movieType The movie type entered by the user.
     * @return True if the movie type is valid, false otherwise.
     */
    private boolean isValidMovieType(String movieType) {
        for (MovieType type : MovieType.values()) {
            if (type.name().equalsIgnoreCase(movieType)) {
                return true;
            }
        }
        return false;
    }
}
