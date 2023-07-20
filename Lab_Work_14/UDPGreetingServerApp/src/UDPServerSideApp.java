import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import solution_Part2_Exercise_2_controller.SentenceProcessor;

/**
 * 
 * @Solution  Part2 Exercise_2
 * 
 * UDPServerSideApp is a server-side application that demonstrates UDP communication with a client.
 * It receives a sentence from the client, processes it, and sends back the total character count of the sentence.
 * 
 * @author Khalid
 */
public class UDPServerSideApp {

    public static void main(String[] args) {

        System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side Application.");

        int portNo = 1177;

        try {

            // Create a DatagramSocket to listen for incoming packets
            DatagramSocket datagramSocket = new DatagramSocket(portNo);

            while (true) {
                byte[] receivedData = new byte[65535];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

                // Receive the packet from the client
                datagramSocket.receive(receivedPacket);

                receivedData = receivedPacket.getData();

                // Process the received data
                SentenceProcessor processor = new SentenceProcessor(receivedData);
                String sentence = processor.getSentence();
                System.out.println("\nMessage received: " + sentence + ".\n");

                // Calculate the total character count
                int totalCharacters = processor.countCharacters();
                byte[] outData = processor.convertToByteArray(totalCharacters);

                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();
                int sizeOutData = outData.length;

                // Create a packet to send the result back to the client
                DatagramPacket outPacket = new DatagramPacket(outData, sizeOutData, clientAddress, clientPort);

                // Send the packet to the client
                datagramSocket.send(outPacket);
                System.out.println("Message sent (totalCharacters): " + totalCharacters + ".\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("UDPServerSideApp: End of program.");
    }
}
