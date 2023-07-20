package server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Customer;
import server.controller.CustomerDataController;

/**
 * 
 * @Solution  Exercise 6
 * 
 * This class represents a TCP server application that listens for client requests to retrieve customer information by ID.
 * It waits for client connections, receives customer ID requests, and sends back the corresponding customer information.
 * 
 * @author Khalid
 */
public class TCPCustomerIDServerApp {

    public static void main(String[] args) {
        int portNo = 1111;
        CustomerDataController manager = new CustomerDataController();

        System.out.println("\n\tExecuting TCPCustomerIDServerApp");

        try {
            System.out.println("\tWaiting the next request");

            ServerSocket serverSocket = new ServerSocket(portNo);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Receive customer ID request from the client
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                int customerId = dis.readInt();
                System.out.println("\n\t Requesting the customer's ID: " + customerId);

                // Retrieve customer information by ID from the manager
                Customer customer = manager.getCustomerById(customerId);

                // Send the customer information back to the client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customer);

                System.out.print("\t Sending customer data: " + customer.getId() + " " + customer.getName() + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
