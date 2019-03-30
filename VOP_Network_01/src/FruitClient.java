import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FruitClient {

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 3333);
            Scanner keyboard = new Scanner(System.in);
            Scanner fromServerStream = new Scanner(clientSocket.getInputStream());
            ObjectOutputStream toServerStream = new ObjectOutputStream(clientSocket.getOutputStream())){

            // Connection made
            System.out.println("Connection established to server on port: " + clientSocket.getPort());
            while(true) {

                // Check to see if server has a respond, if true, print the response.
                System.out.println(fromServerStream.nextLine());

                // Retrieve client input and convert it to a Fruit, then send it to server.
                try {
                    String[] input = keyboard.nextLine().split(";");
                    Fruit fruit = new Fruit(input[0].trim(), input[1].trim(), Integer.valueOf(input[2]), Boolean.valueOf(input[3]));
                    toServerStream.writeObject(fruit);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Wrong format");
                }

                // If the server has a response, print it to client.
                System.out.println(fromServerStream.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
