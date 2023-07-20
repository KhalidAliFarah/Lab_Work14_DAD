package client.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import model.Customer;

/**
 * 
 * @Solution  Exercise 5
 * 
 * This class represents a TCP client application that connects to a server and retrieves customer information.
 * It prompts the user to enter a name and sends it to the server. The server responds with the corresponding customer
 * information, which is then displayed to the user.
 * 
 * @author Khalid
 */
public class TCPCustomerNameClientApp {

    public static void main(String[] args) {
        String name = "";
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        DataOutputStream dos = null;
        ObjectInputStream ois = null;

        try {
            System.out.println("\tExecuting TCPCustomerNameClientApp");

            int serverPortNo = 1122;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // Establish a connection with the server
            socket = new Socket(serverAddress, serverPortNo);

            // Get the output stream to send data to the server
            OutputStream os = socket.getOutputStream();
            dos = new DataOutputStream(os);

            // Prompt the user to enter a name
            System.out.print("\tPlease Enter name: ");
            name = scanner.nextLine();

            // Send the name to the server
            dos.writeUTF(name);

            // Get the input stream to receive data from the server
            InputStream is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            // Read the Customer object sent by the server
            Customer customer = (Customer) ois.readObject();

            System.out.println("\n\t Customer data (From the server)");

            // Display the customer information
            if (customer != null) {
                System.out.println("\tCustomer Id: " + customer.getId());
                System.out.println("\tName: " + customer.getName());
            } else {
                System.out.println("\tCustomer Id: - ");
                System.out.println("\tName: No customer found");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close all open resources
            try {
                if (dos != null)
                    dos.close();
                if (ois != null)
                    ois.close();
                if (socket != null)
                    socket.close();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
