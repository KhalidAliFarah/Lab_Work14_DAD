package client.app;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import model.Customer;
import client.view.CustomerViewer;

/**
 * 
 * @Solution  Exercise 10
 * 
 * This class represents a TCP client application that connects to a server and retrieves a list of customers.
 * It establishes a connection with the server, receives the customer data, and displays it using the CustomerViewer class.
 * 
 * @author Khalid
 */
public class TCPCustomersClientApp {

    public static void main(String[] args) {
        try {
            // Server information
            int serverPortNo = 1133;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // Connect to the server
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Receive the list of customers from the server
            List<Customer> customers = receiveCustomers(socket);

            // Display the customer data
            displayCustomers(customers);

            // Close the socket
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Receives the list of customers from the server.
     *
     * @param socket The socket connected to the server.
     * @return The list of customers received from the server.
     * @throws Exception If an error occurs while receiving the customers.
     */
    private static List<Customer> receiveCustomers(Socket socket) throws Exception {
        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        List<Customer> customers = (List<Customer>) ois.readObject();
        ois.close();
        return customers;
    }

    /**
     * Displays the list of customers using the CustomerViewer class.
     *
     * @param customers The list of customers to be displayed.
     */
    private static void displayCustomers(List<Customer> customers) {
        CustomerViewer customerViewer = new CustomerViewer();
        customerViewer.displayCustomers(customers);
    }
}
