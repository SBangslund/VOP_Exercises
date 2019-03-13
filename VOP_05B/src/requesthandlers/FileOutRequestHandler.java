package requesthandlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOutRequestHandler extends AbstractRequestHandler {

    private final File file;

    public FileOutRequestHandler(Socket socket, String filename) {
        super(socket);
        file = new File(filename);
    }

    @Override
    public void run() {
        System.out.println("RequestHandler started for " + socket.getPort());
        try (Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            writer.println("Server ready. Type your message:");
            
            try (PrintWriter fileWriter = new PrintWriter(file)) {
                fileWriter.append(String.format("[%s]\t[%s:%s] : %s", new Date(), socket.getInetAddress(), socket.getPort(), scanner.nextLine()));
            } catch (IOException e) {
                System.err.println("Error fetching the file: " + file.getAbsolutePath());
            }
            writer.println("Logged your answer!");
            System.out.println("FileRequestHandler done... (Written to file)");
        } catch (IOException ex) {
            Logger.getLogger(FlipRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
