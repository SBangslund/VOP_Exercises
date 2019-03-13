/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */
package requesthandlers;

import java.io.File;
import tcp_classes.Species;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel Bangslund
 */
public class ObjectRequestHandler extends AbstractRequestHandler {

    private final File file;

    public ObjectRequestHandler(Socket socket, String filename) {
        super(socket);
        this.file = new File(filename);
    }

    @Override
    public void run() {
        System.out.println("ObjectRequestHandler started for " + socket.getPort());
        try (ObjectInputStream fromClientStream = new ObjectInputStream(socket.getInputStream());
                PrintWriter toClientStream = new PrintWriter(socket.getOutputStream(), true)) {
            toClientStream.println("Server ready. Type species to send(name, population, growthRate): ");

            try {
                // Retrieve the object from the client connection.
                Species species = (Species) fromClientStream.readObject();
                // Write the object to a file. Inform status to client and console.
                if (writeToFile(file, species.toString())) {
                    System.out.println("Successfully added " + species);    // Tell the console
                    toClientStream.println("Successfully added " + species);// Tell the client
                } else {
                    System.out.println("Failed to add " + species);         // Tell the console
                    toClientStream.println("Failed to add " + species);     // Tell the client
                }
            } catch (ClassNotFoundException ex) {
                // Client did not send a Species object.
                System.err.println("Client did not send a Species type.");
            }
        } catch (IOException ex) {
            Logger.getLogger(FlipRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean writeToFile(File file, String data) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("Failed to create a new file.");
            }
        }
        boolean success = false;
        List<String> fileData = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            scanner.forEachRemaining(fileData::add);
            try (PrintWriter writer = new PrintWriter(file)) {
                fileData.forEach(writer::append);
                writer.append(data);
                success = true;
            } catch (IOException e) {
                System.err.println("Error writing to file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + file.getAbsolutePath());
        }
        return success;
    }
}
