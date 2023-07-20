package server.app;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.Customer;
import server.controller.CustomerDataController;

/**
 * 
 * @Solution  Exercise 9
 * 
 * This class represents a TCP server application that listens for client requests to retrieve a list of customers.
 * It waits for client connections, retrieves the list of customers from the manager, and sends it back to the client.
 * 
 * author Khalid
 */
public class TCPCustomersServerApp {

    public static void main(String[] args) {
        int portNo = 1133;

        System.out.println("\n\tExecuting TCPCustomersServerApp");

        CustomerDataController manager = new CustomerDataController();

        try {
            System.out.println("\n\tWaiting for next request");

            ServerSocket serverSocket = new ServerSocket(portNo);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Retrieve the list of customers from the manager
                List<Customer> customers = manager.getCustomers();

                // Send the list of customers back to the client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customers);

                System.out.println("\tSending: " + customers.size() + " customers");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
