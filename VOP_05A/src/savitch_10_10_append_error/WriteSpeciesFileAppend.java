package savitch_10_10_append_error;

import java.io.*;
import java.util.*;
import saviich_10_9.Species;

public class WriteSpeciesFileAppend {

    public static void main(String[] args) {
        String fileName = getFileName("Enter output file name.");
        File file = new File(fileName);

        try (ObjectOutputStream outputStream = file.exists() ? new ObjectOutputStream(new FileOutputStream(file, true)) {
                @Override
                protected void writeStreamHeader() throws IOException {
                    super.reset();
                }
            } : new ObjectOutputStream(new FileOutputStream(file, true))) {

            Species califCondor = new Species("Calif. Condor", 27, 0.02);
            outputStream.writeObject(califCondor);

            Species blackRhino = new Species("Black Rhino", 100, 1.0);
            outputStream.writeObject(blackRhino);
            
            Species horse = new Species("Horse", 300, 1.3);
            outputStream.writeObject(horse);

        } catch (IOException e) {
            System.err.println("Error opening output file " + file.getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Records sent to file " + file.getName() + ".");
        System.out.println("Now let's reopen the file and echo the records.");

        Species readOne;
        int records = 0;
        
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {  //Reads untill EOF
                readOne = (Species) inputStream.readObject();
                System.out.println(readOne + "\n");
                records++;
            }
        } catch (EOFException eof) {
            System.out.println("Reading Done! " + records);
        } catch (IOException e) {
            System.err.println("Error opening input file " + file.getName() + ": " + e.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }

    }

    private static String getFileName(String prompt) {
        Scanner keyboard = new Scanner(System.in);
        String fileName = null;
        System.out.println(prompt);
        fileName = keyboard.next();

        return fileName;
    }
}
