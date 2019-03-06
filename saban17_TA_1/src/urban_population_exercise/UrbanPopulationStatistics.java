package urban_population_exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Udleveret kodeskelet til VOP re-eksamen 20. august 2014
 */
public class UrbanPopulationStatistics {

    private final Set<UrbanPopulation> popSet;
    private final File file;

    public UrbanPopulationStatistics(String fileName) {
        popSet = new TreeSet<>();
        file = new File(fileName);
        readFile();
    }

    private void readFile() {
        int lineCounter = 1;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split("/");
                try {
                    popSet.add(new UrbanPopulation(words[0], Integer.valueOf(words[1]), Integer.valueOf(words[4])));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.err.println("Wrong format on line: " + lineCounter + " in file: " + getFile().getAbsolutePath());
                }
                lineCounter++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find file: " + file.getAbsolutePath());
        }
    }

    // Udleveret toString() metode, som giver en "pæn" formatering.
    @Override
    public String toString() {
        String s = popSet.toString().replaceAll(", ", "");
        return "UrbanPopulationStatistics:\n" + s.substring(1, s.length() - 1) + "\n";
    }

    // Udleveret test-metode
    public static void main(String[] args) {
        UrbanPopulationStatistics stats = new UrbanPopulationStatistics("ByBefolkning.txt");
        System.out.println(stats);
    }

    // Antager, eftersom variablerne skulle instantieres ved constructoren, at der kun skal laves accessors.
    // Har derfor også lavet variablerne final.
    
    public Set<UrbanPopulation> getPopSet() {
        return popSet;
    }

    public File getFile() {
        return file;
    }
}
