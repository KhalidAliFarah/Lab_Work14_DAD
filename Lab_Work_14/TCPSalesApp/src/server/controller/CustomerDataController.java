


package server.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

/**
 * 
 * @Solution  Exercise 3
 * 
 * This class represents a data manager for managing customer data.
 * It provides methods to retrieve customer information by name or ID.
 * 
 * author Khalid
 */
public class CustomerDataController implements Serializable {

    private List<Customer> customerList;

    /**
     * Constructs a CustomerDataController object.
     * Initializes the customer list and loads sample customers.
     */
    public CustomerDataController() {
        this.customerList = new ArrayList<>();
        loadCustomers();
    }

    /**
     * Retrieves the list of customers.
     *
     * @return The list of customers.
     */
    public List<Customer> getCustomers() {
        return customerList;
    }

    /**
     * Retrieves a customer by name.
     *
     * @param name The name of the customer.
     * @return The customer object if found, or null if not found.
     */
    public Customer getCustomerByName(String name) {
        for (Customer customer : customerList) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id The ID of the customer.
     * @return The customer object if found, or a customer with "Customer not found" name if not found.
     */
    public Customer getCustomerById(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        Customer customer = new Customer();
        customer.setName("Customer not found");
        return customer;
    }

    /**
     * Loads sample customers into the customer list.
     * This is for demonstration purposes.
     */
    private void loadCustomers() {
        String[] nameList = {
                "Ali Ahmed", "Mohamed Khalid", "Khalid Ali",
                "Mohamed Sherif", "Fatima Abdullah", "Khalid Saeed",
                "Abdullah Ahmed", "Zainab Ali",
                "Sara Mohamed", "Layla Ahmed"
        };
        for (int index = 0; index < nameList.length; index++) {
            Customer customer = new Customer();
            customer.setId(index + 1);
            customer.setName(nameList[index]);
            customerList.add(customer);
        }
    }
}
