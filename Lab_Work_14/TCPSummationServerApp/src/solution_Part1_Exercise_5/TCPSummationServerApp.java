package solution_Part1_Exercise_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import solution_Part1_Exercise_5_controller.NumberCalculator;

/**
 * 
 * @Solution  Part1 Exercise_5 
 * 
 * This class represents a TCP server application that calculates the summation
 * and multiplication of three numbers received from a client.
 * 
 * @author Khalid
 */
public class TCPSummationServerApp {

    private static final int PORT_NUMBER = 1166;

    /**
     * The main entry point of the server application.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("\n\tExecuting TCPSummationServerApp");

        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            NumberCalculator numberCalculator = new NumberCalculator();

            System.out.println("\tWaiting for request");

            while (true) {
                // Wait for a client request
                Socket clientRequest = serverSocket.accept();
                // Process the client request
                processClientRequest(clientRequest, numberCalculator);
                System.out.println("\n\tWaiting for next request");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Processes the client request by calculating the summation and multiplication
     * of three numbers and sending the results back to the client.
     *
     * @param clientRequest     The socket representing the client request.
     * @param numberCalculator  The instance of the NumberCalculator class.
     */
    private static void processClientRequest(Socket clientRequest, NumberCalculator numberCalculator) {
    	
        try (InputStream inputStream = clientRequest.getInputStream();
        		
             DataInputStream dataInputStream = new DataInputStream(inputStream);
        		
             OutputStream outputStream = clientRequest.getOutputStream();
        		
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {

            // Read three numbers from the client
            int number1 = dataInputStream.readInt();
            int number2 = dataInputStream.readInt();
            int number3 = dataInputStream.readInt();
            System.out.println("\tFrom client: " + number1 + ", " + number2 + ", " + number3);

            // Calculate the summation and multiplication
            int total = numberCalculator.getSum(number1, number2, number3);
            int multiply = numberCalculator.getMultiplication(number1, number2, number3);

            // Send the results back to the client
            dataOutputStream.writeInt(total);
            dataOutputStream.writeInt(multiply);
            System.out.println("\tTo client: Summation: " + total + ", Multiplication: " + multiply);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
