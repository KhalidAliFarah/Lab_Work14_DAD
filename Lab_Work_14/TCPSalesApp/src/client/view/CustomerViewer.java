package client.view;

import java.util.List;

import model.Customer;

/**
 * This class is responsible for displaying customer information to the console.
 * It provides a method to display a list of customers with their IDs and names.
 * 
 * @author Khalid
 */
public class CustomerViewer {

    /**
     * Displays the list of customers to the console.
     *
     * @param customers The list of customers to be displayed.
     */
    public void displayCustomers(List<Customer> customers) {
        System.out.println("\n\tNumber of records: " + customers.size());
        System.out.println("\tCustomer Information\n");

        for (Customer customer : customers) {
            System.out.println("\tCustomer Id: " + customer.getId());
            System.out.println("\tCustomer Name: " + customer.getName() + "\n");
        }
    }
}
