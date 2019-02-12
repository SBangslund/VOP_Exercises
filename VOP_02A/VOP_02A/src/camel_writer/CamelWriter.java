package camel_writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CamelWriter {

    private final File inFile;

    public CamelWriter(String fName) {
        inFile = new File("src//camel_writer//" + fName);
    }

    public void readLines() {
        try {
            Scanner scanner = new Scanner(inFile);
            while (scanner.hasNextLine()) {
                convert2camel(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Could not find the file:" + inFile.getAbsolutePath());
        }
    }

    private void convert2camel(String line) {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(line);
        
        String firstWord = scanner.hasNext() ? scanner.next() : " ";
        builder.append(Character.toLowerCase(firstWord.toCharArray()[0]));
        builder.append(firstWord.subSequence(1, firstWord.toCharArray().length));
        
        while(scanner.hasNext()) {
            String word = scanner.next();
            word = word.replaceFirst(Character.toString(word.toCharArray()[0]), Character.toString(Character.toUpperCase(word.toCharArray()[0])));
            builder.append(word);
        }

        System.out.println(builder.toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CamelWriter cw = new CamelWriter("OhLand.txt");
        cw.readLines();
    }

}
