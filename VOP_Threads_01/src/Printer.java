import java.io.*;

public class Printer {

    private final StringBuilder builder = new StringBuilder();

    public synchronized void write(String text) {
        builder.append(text);
    }

    public void printToFile(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file, true))) {
            outputStream.writeObject(builder.toString());
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    public StringBuilder getBuilder() {
        return this.builder;
    }
}
