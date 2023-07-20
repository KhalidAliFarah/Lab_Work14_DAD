package client.view;

import java.util.List;

import model.Product;

/**
 * This class is responsible for displaying product information to the console.
 * It provides a method to display a list of products with their IDs, names, and prices.
 * 
 * @author Khalid
 */
public class ProductViewer {

    /**
     * Displays the list of products to the console.
     *
     * @param products The list of products to be displayed.
     */
    public void displayProducts(List<Product> products) {
        System.out.println("\tRecords: " + products.size());
        System.out.println("\tProduct Details\n");

        for (Product product : products) {
            System.out.println("\tProduct ID: " + product.getProductId());
            System.out.println("\tProduct Name: " + product.getName());
            System.out.println("\tProduct Price: RM" + product.getPrice() + "\n");
        }
    }
}
