package server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.Product;
import server.controller.ProductDataManager;

/**
 * This class represents a TCP server application that listens for client requests to retrieve a list of products.
 * It waits for client connections, retrieves the list of products from the manager, and sends it back to the client.
 * 
 * author Khalid
 */
public class TCPProductsServerApp {

    public static void main(String[] args) {
        int portNo = 1155;
        ProductDataManager manager = new ProductDataManager();

        System.out.println("\n\tExecuting TCPProductsServerApp");

        try {
            System.out.println("\tWaiting the next request");

            ServerSocket serverSocket = new ServerSocket(portNo);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Retrieve the list of products from the manager
                List<Product> products = manager.getProducts();

                // Send the list of products back to the client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(products);

                System.out.println("\tSending: " + products.size() + " products");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
