package tcp_classes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        String str;
        Scanner fromServerStream = null;
        ObjectOutputStream toServerStream = null;

        try (Socket clientSocket = new Socket("localhost", 3333);
                Scanner keyboard = new Scanner(System.in)) {
            // Set up streams to send/receive data
            fromServerStream = new Scanner(clientSocket.getInputStream());
            toServerStream = new ObjectOutputStream(clientSocket.getOutputStream());

            // Start massage from server:
            System.out.println("Connection made.");
            System.out.println(fromServerStream.nextLine());
            
            // Read line from client and create a Species object from it.
            String[] words = keyboard.nextLine().split(",");
            Species species = new Species(words[0].trim(), Integer.valueOf(words[1].trim()), Double.valueOf(words[2].trim()));
            
            // Send object to server.
            toServerStream.writeObject(species);

            // Read answer from the server and output it to the screen
            str = fromServerStream.nextLine();
            System.out.println(str);

        } catch (Exception e) {
            // If any exception occurs, display it
            System.err.println("Error " + e);
        } finally {
            fromServerStream.close();
            toServerStream.close();
        }
    }
}
