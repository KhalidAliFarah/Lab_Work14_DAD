package server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Product;
import server.controller.ProductDataManager;

/**
 * This class represents a TCP server application that listens for client requests to retrieve product information by ID.
 * It waits for client connections, receives product ID requests, and sends back the corresponding product information.
 * 
 * author Khalid
 */
public class TCPProductServerApp {

    public static void main(String[] args) {
        int portNo = 1144;
        ProductDataManager manager = new ProductDataManager();

        System.out.println("\n\tExecuting TCPProductServerApp");

        try {
            System.out.println("\tWaiting the next request");

            ServerSocket serverSocket = new ServerSocket(portNo);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Receive product ID request from the client
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                int productId = dis.readInt();
                System.out.println("\tRequesting the customer's id: " + productId);

                // Retrieve product information by ID from the manager
                Product product = manager.getProduct(productId);

                // Send the product information back to the client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(product);

                System.out.print("\tSending product: " + product.getProductId() + " " + product.getName());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
