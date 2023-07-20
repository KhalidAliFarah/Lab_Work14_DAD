import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDPClientSideApp is a demonstration of a UDP client-side application. 
 * It sends a message to a server and receives a response containing the length of the message.
 * 
 * @author Khalid
 */
public class UDPClientSideApp {

    public static void main(String[] args) {
        System.out.println("\n\tUDPClientSideApp: Demonstration of UDP Client-Side Application.");

        try {
            int portNo = 1177;
            InetAddress ip = InetAddress.getLocalHost();
            String text = "Good morning Malaysia Singapore Vietnam";
            byte[] buf = text.getBytes();

            // Create a DatagramPacket to send the data to the server
            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, ip, portNo);
            DatagramSocket datagramSocket = new DatagramSocket();

            // Send the packet to the server
            datagramSocket.send(outPacket);
            System.out.println("\n\tSending '" + text + "' (" + text.length() + ") out on the network.");

            // Receive the response from the server
            byte[] inData = new byte[65535];
            DatagramPacket inPacket = new DatagramPacket(inData, inData.length);

            datagramSocket.receive(inPacket);

            // Extract the received data and convert it to a string
            String receivedData = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("\tLength from the server is: " + receivedData);

            // Close the socket
            datagramSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\tUDPClientSideApp: End of program.");
    }
}
