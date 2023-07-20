package client.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import model.Customer;

/** 
 * 
 * @Solution  Exercise 7
 * 
 * This class represents a TCP client application that communicates with a server
 * to retrieve customer information based on a provided customer ID.
 * It establishes a socket connection with the server and sends/receives data over the network.
 *
 * @author Khalid
 */
public class TCPCustomerIDClientApp {

    public static void main(String[] args) {
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        DataOutputStream dos = null;
        ObjectInputStream ois = null;

        try {
            System.out.println("\tExecuting TCPCustomerIDClientApp");

            int serverPortNo = 1111;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // Create a socket connection to the server
            socket = new Socket(serverAddress, serverPortNo);

            // Get the output stream from the socket
            OutputStream outputStream = socket.getOutputStream();
            dos = new DataOutputStream(outputStream);

            System.out.print("\tEnter id: ");
            id = scanner.nextInt();

            // Send the customer ID to the server
            dos.writeInt(id);

            // Get the input stream from the socket
            InputStream is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            // Read the customer object received from the server
            Customer customer = (Customer) ois.readObject();

            System.out.println("\n\tCustomer Information (From the server)");
            System.out.println("\tCustomer Id: " + customer.getId());
            System.out.println("\tName: " + customer.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close all the open resources
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
