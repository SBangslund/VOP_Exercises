package danish_islands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DanishIslandFileReader {

    private final File inFile;
    private final List<DanishIsland> islandList = new ArrayList<>();

    public DanishIslandFileReader(String fName) {
        inFile = new File(fName);
    }

    private void readFile() {
        try {
            Scanner scanner = new Scanner(inFile);
            while (scanner.hasNextLine()) {
                Scanner r = new Scanner(scanner.nextLine());
                try {
                    islandList.add(new DanishIsland(r.next(), r.nextDouble(), r.nextDouble(), r.nextInt(), r.nextInt()));
                } catch (NumberFormatException ex) {
                    System.err.println("Line not compatible.");
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Could not load file: " + inFile.getAbsolutePath());
        }
    }

    public List<?> getList() {
        return islandList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DanishIslandFileReader fr = new DanishIslandFileReader("Islands punktum.txt");
        //DanishIslandFileReader fr = new DanishIslandFileReader("Islands komma.txt");
        fr.readFile();

        System.out.println("Result:\n" + fr.getList());
    }
}
