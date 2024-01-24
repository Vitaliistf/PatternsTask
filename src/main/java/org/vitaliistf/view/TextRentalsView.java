package org.vitaliistf.view;

import org.vitaliistf.model.Customer;
import org.vitaliistf.model.Rental;

/**
 * Text implementation of the RentalsView interface for generating rentals-related text output for a customer.
 */
public class TextRentalsView implements RentalsView {

    /**
     * Singleton instance of TextRentalsView.
     */
    private static TextRentalsView instance;

    /**
     * Private constructor to make object producible only inside the class.
     */
    private TextRentalsView() {
    }

    /**
     * Method to get singleton instance of TextRentalsView.
     *
     * @return TextRentalsView instance.
     */
    public static TextRentalsView getInstance() {
        if (instance == null) {
            instance = new TextRentalsView();
        }
        return instance;
    }

    /**
     * Generates the text header for the rentals output.
     *
     * @param customer The customer for whom the header is generated.
     * @return The text header as a string.
     */
    @Override
    public String generateHeader(Customer customer) {
        return "Rental records for " +
                customer.name() +
                "\n";
    }

    /**
     * Generates the text rental records for the rentals output.
     *
     * @param customer The customer for whom the records are generated.
     * @return The text rental records as a string.
     */
    @Override
    public String generateRecords(Customer customer) {
        StringBuilder sb = new StringBuilder();
        for (Rental each : customer.rentals()) {
            sb.append("\t")
                    .append(each.movie().getTitle())
                    .append("\t")
                    .append(each.calculatePrice())
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates the text footer for the rentals output.
     *
     * @param customer The customer for whom the footer is generated.
     * @return The text footer as a string.
     */
    @Override
    public String generateFooter(Customer customer) {
        return "Amount owed is " +
                customer.getTotalAmount() +
                ".\n" +
                "You earned " +
                customer.getPoints() +
                " points.";
    }
}
