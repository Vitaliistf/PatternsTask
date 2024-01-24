package org.vitaliistf.view;

import org.vitaliistf.model.Customer;

/**
 * Interface for generating rentals-related output for a customer.
 */
public interface RentalsView {

    /**
     * Generates the complete rentals output for a customer, including header, records, and footer.
     *
     * @param customer The customer for whom the rentals output is generated.
     * @return The complete rentals output as a string.
     */
    default String generateRentalsOutput(Customer customer) {
        return generateHeader(customer) +
                generateRecords(customer) +
                generateFooter(customer);
    }

    /**
     * Generates the header for the rentals output.
     *
     * @param customer The customer for whom the header is generated.
     * @return The header as a string.
     */
    String generateHeader(Customer customer);

    /**
     * Generates the rental records for the rentals output.
     *
     * @param customer The customer for whom the records are generated.
     * @return The rental records as a string.
     */
    String generateRecords(Customer customer);

    /**
     * Generates the footer for the rentals output.
     *
     * @param customer The customer for whom the footer is generated.
     * @return The footer as a string.
     */
    String generateFooter(Customer customer);
}
