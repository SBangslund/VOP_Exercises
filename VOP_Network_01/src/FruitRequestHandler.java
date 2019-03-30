import java.io.*;
import java.net.Socket;

public class FruitRequestHandler implements Runnable{

    private final Socket socket;
    private final File file;

    public FruitRequestHandler(Socket socket, File file) {
        this.socket = socket;
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println("FruitRequestHandler started for " + socket.getPort());

        try (ObjectInputStream fromClientStream = new ObjectInputStream(socket.getInputStream());
                PrintWriter toClientStream = new PrintWriter(socket.getOutputStream(), true)) {
            while(true) {
                toClientStream.println("Send fruit (name;color;number;edible): ");
                Fruit fruit = (Fruit)fromClientStream.readObject();
                printToFile(fruit);
                System.out.println("Added: " + fruit);
                toClientStream.println("Successfully added " + fruit);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void printToFile(Fruit fruit) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file, true))){
            outputStream.writeObject(fruit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
