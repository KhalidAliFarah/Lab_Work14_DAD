package solution_Part2_Exercise_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * 
 * @Solution  Part2 Exercise_2
 * 
 * UDPCountCharacterClientApp is a client-side application 
 * that demonstrates UDP communication with a server.
 * It sends a sentence to the server and receives the result, 
 * which is the character count of the sentence.
 * 
 * @author Khalid
 */
public class UDPCountCharacterClientApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\tUDPCountCharacterClientApp: Demonstration of UDP Client-Side Application.");

        try {
            int portNo = 1177;
            InetAddress ip = InetAddress.getLocalHost();

            System.out.print("\tWrite a sentence: ");
            String text = scanner.nextLine();
            byte[] buf = text.getBytes();

            // Create a DatagramPacket to send the data to the server
            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, ip, portNo);
            DatagramSocket datagramSocket = new DatagramSocket();

            // Send the packet to the server
            datagramSocket.send(outPacket);
            System.out.println("\n\tSending '" + text + "' (" + text.length() + ") out on the network.");

            // Receive the response from the server
            byte[] inData = new byte[100000];
            DatagramPacket inPacket = new DatagramPacket(inData, inData.length);

            datagramSocket.receive(inPacket);

            // Extract the received data and convert it to a string
            String result = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("\tResult from the server is: " + result);

            // Close the socket and scanner
            datagramSocket.close();
            scanner.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("\n\tUDPCountCharacterClientApp: End of program.");
    }
}
