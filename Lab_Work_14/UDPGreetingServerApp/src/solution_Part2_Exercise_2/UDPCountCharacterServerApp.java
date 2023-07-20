package solution_Part2_Exercise_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import solution_Part2_Exercise_2_controller.SentenceProcessor;

/**
 * 
 * @Solution  Part2 Exercise_2
 * 
 * UDPCountCharacterServerApp is a server application 
 * that processes sentences received from clients, providing 
 * character, vowel, consonant, and punctuation counts.
 * 
 * @author Khalid
 */
public class UDPCountCharacterServerApp {

    public static void main(String[] args) {

        System.out.println("\tUDPCountCharacterServerApp: "
        		+ "Demonstration of UDP Server-Side Application.");

        int portNo = 1177;

        try {
            // Create a DatagramSocket to listen for incoming packets
            DatagramSocket datagramSocket = new DatagramSocket(portNo);

            while (true) {
                // Create a byte array to store the received data
                byte[] receivedData = new byte[100000];

                // Create a DatagramPacket to store the received packet
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, 
                		receivedData.length);

                // Receive the packet from the client
                datagramSocket.receive(receivedPacket);

                // Extract the received data from the packet
                receivedData = receivedPacket.getData();

                // Process the received data using the SentenceProcessor
                SentenceProcessor sentenceProcessor = new SentenceProcessor(receivedData);

                // Get the sentence from the processed data
                String sentence = sentenceProcessor.getSentence();
                System.out.println("\n\tMessage received: " + sentence + ".\n");

                // Calculate the counts using the SentenceProcessor
                int totalCharacters = sentenceProcessor.countCharacters();
                int totalVowels = sentenceProcessor.countVowels();
                int totalConsonants = sentenceProcessor.countConsonants();
                int totalPunctuations = sentenceProcessor.countPunctuations();

                // Prepare the result string
                String result = "\n\tTotal Characters: " + totalCharacters +
                        "\n\tTotal Vowels: " + totalVowels +
                        "\n\tTotal Consonants: " + totalConsonants +
                        "\n\tTotal Punctuations: " + totalPunctuations;

                // Convert the result string to byte array
                byte[] outData = result.getBytes();

                // Get the client address and port from the received packet
                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();
                int sizeOutData = outData.length;

                // Create a DatagramPacket to send the result back to the client
                DatagramPacket outPacket = new DatagramPacket(outData, sizeOutData, 
                		clientAddress, clientPort);

                // Send the packet to the client
                datagramSocket.send(outPacket);
                System.out.println("\tMessage sent: " + result + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("UDPClientSideApp: End of program.");
    }
}
