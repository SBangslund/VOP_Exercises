package tcp_server_runnable;

import requesthandlers.AbstractRequestHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import requesthandlers.FileOutRequestHandler;
import requesthandlers.FlipRequestHandler;
import requesthandlers.ObjectRequestHandler;

public class TCPServer {

    private int port;
    private AbstractRequestHandler requestHandler;

    public TCPServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket ss = new ServerSocket(port)) {
            while (true) {
                System.out.println("Server waiting....");
                Socket socket = ss.accept();
                System.out.println("Server has accepted a client on port " + socket.getPort());

                // Opgave 1.
                //requestHandler = new FlipRequestHandler(socket);
                // Opgave 2.
                //requestHandler = new FileOutRequestHandler(socket, "Log.obj");
                // Opgave 3.
                requestHandler = new ObjectRequestHandler(socket, "Species.obj");

                new Thread(requestHandler).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer(3333);
        server.start();
    }
}
