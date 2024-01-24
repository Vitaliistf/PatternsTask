package org.vitaliistf.view;

import org.vitaliistf.model.Customer;
import org.vitaliistf.model.Rental;

/**
 * HTML implementation of the RentalsView interface for generating rentals-related HTML output for a customer.
 */
public class HtmlRentalsView implements RentalsView {

    /**
     * Singleton instance of HtmlRentalsView.
     */
    private static HtmlRentalsView instance;

    /**
     * Private constructor to make object producible only inside the class.
     */
    private HtmlRentalsView() {
    }

    /**
     * Method to get singleton instance of HtmlRentalsView.
     *
     * @return HtmlRentalsView instance.
     */
    public static HtmlRentalsView getInstance() {
        if (instance == null) {
           instance = new HtmlRentalsView();
        }
        return instance;
    }


    /**
     * Generates the HTML header for the rentals output.
     *
     * @param customer The customer for whom the header is generated.
     * @return The HTML header as a string.
     */
    @Override
    public String generateHeader(Customer customer) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Rental record for\s""" +
                customer.name() +
                """
                        </title>
                        </head>
                        <body>
                            <h1>Rental record for\s""" +
                customer.name() +
                """
                        </h1>
                        """;
    }

    /**
     * Generates the HTML rental records for the rentals output.
     *
     * @param customer The customer for whom the records are generated.
     * @return The HTML rental records as a string.
     */
    @Override
    public String generateRecords(Customer customer) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (Rental each : customer.rentals()) {
            sb.append("<li>")
                    .append(each.movie().getTitle())
                    .append(" - ")
                    .append(each.calculatePrice())
                    .append("</li>\n");
        }
        sb.append("</ul>\n");
        return sb.toString();
    }

    /**
     * Generates the HTML footer for the rentals output.
     *
     * @param customer The customer for whom the footer is generated.
     * @return The HTML footer as a string.
     */
    @Override
    public String generateFooter(Customer customer) {
        return """
                <p>Amount owed is <strong>""" +
                customer.getTotalAmount() +
                """
                        </strong>.</p>
                        <p>You earned <strong>""" +
                customer.getPoints() +
                """
                        </strong> points.</p>
                        </body>
                        </html>""";
    }
}
