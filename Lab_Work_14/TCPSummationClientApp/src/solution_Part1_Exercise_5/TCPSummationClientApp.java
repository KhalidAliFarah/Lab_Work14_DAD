/**
 * 
 * @Solution  Part1 Exercise_5 
 * 
 * This class represents a TCP client application that connects to a server
 * to perform summation and multiplication of three numbers.
 * The client prompts the user to input three numbers, sends them to the server,
 * and receives the result (sum and multiply) back from the server.
 * The client then displays the result on the console.
 *
 * @author Khalid
 */
package solution_Part1_Exercise_5;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPSummationClientApp {

    public static void main(String[] args) {
        System.out.println("\n\nExecuting TCPSummationClientApp");
        
        System.out.println("\n #############++ Calclulater ++#############");

        try (Scanner scanner = new Scanner(System.in)) {
            // Get three numbers from the user
            System.out.print("\nType the first digit : ");
            int number1 = scanner.nextInt();

            System.out.print("\nType the second digit: ");
            int number2 = scanner.nextInt();

            System.out.print("\nType the third digit: ");
            int number3 = scanner.nextInt();
            
            

            // Server details
            int serverPortNo = 1166;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // Connect to the server
            try (Socket socket = new Socket(serverAddress, serverPortNo);
            		
                 OutputStream outStream = socket.getOutputStream();
            		
                 DataOutputStream dos = new DataOutputStream(outStream);
            		
                 InputStream inStream = socket.getInputStream();
            		
                 DataInputStream dis = new DataInputStream(inStream)) {

                // Send the three numbers to the server
                dos.writeInt(number1);
                dos.writeInt(number2);
                dos.writeInt(number3);

                // Receive the result (sum and multiply) from the server
                int sum = dis.readInt();
                int multiply = dis.readInt();

                // Display the result
                System.out.println("\nSending to the server: " + number1 + ", "
                		+ "" + number2 + ", " + number3);
                System.out.println("\nReceiving from the server:");
                System.out.println("Total = " + sum);
                System.out.println("Multiply = " + multiply);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("End of execution at TCPSummationClientApp");
    }
}
