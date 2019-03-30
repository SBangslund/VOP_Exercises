import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FruitServer {
    private final int port;

    public FruitServer(int port) {
        this.port = port;
    }

    public FruitServer start() {
        try (ServerSocket ss = new ServerSocket(port)){
            System.out.println("Started server on port: " + port);
            while(true) {
                Socket socket = ss.accept();

                FruitRequestHandler handler = new FruitRequestHandler(socket, new File("fruits.obj"));
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public static void main(String[] args) {
        new FruitServer(3333).start();
    }
}
