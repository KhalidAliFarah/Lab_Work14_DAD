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
 * @Solution  Exercise 4
 * 
 * This class represents a TCP server application that listens for client requests to retrieve customer information by name.
 * It waits for client connections, receives customer name requests, and sends back the corresponding customer information.
 * 
 * author Khalid
 */
public class TCPCustomerNameServerApp {

    public static void main(String[] args) {
        int portNo = 1122;
        CustomerDataController manager = new CustomerDataController();

        System.out.println("\n\tExecuting TCPCustomerNameServerApp");

        try {
            System.out.println("\tWaiting the next request");

            ServerSocket serverSocket = new ServerSocket(portNo);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Receive customer name request from the client
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String name = dis.readUTF();
                System.out.println("\n\tRequesting the customer's name: " + name);

                // Retrieve customer information by name from the manager
                Customer customer = manager.getCustomerByName(name);

                // Send the customer information back to the client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customer);

                // Print the customer information sent to the client
                if (customer != null) {
                    System.out.print("\tSending customer data: " + customer.getId() + " " + customer.getName() + "\n");
                } else {
                    System.out.print("\tSending customer data: " + customer + "\n");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
