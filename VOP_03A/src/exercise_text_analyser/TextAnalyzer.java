package exercise_text_analyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TextAnalyzer {

    private final File file;

    public TextAnalyzer(String fileName) {
        file = new File(fileName);
    }

    // Opgave 2A: Parameteren sorted afgør om der skal benyttes et sorteret Set
    public Set<String> findUniqueWords(boolean sorted) {
        Set<String> set = sorted ? new TreeSet<>() : new HashSet<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.forEachRemaining(word -> set.add(clean(word)));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getAbsolutePath());
        }
        return set;
    }

    // Opgave 2B: Nearly as Listing 21.9 from Liang
    public Map<String, Integer> countWords(boolean sorted) {
        Map<String, Integer> map = sorted ? new TreeMap<>() : new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.forEachRemaining(word -> {
                word = clean(word);
                if (map.containsKey(word)) {
                    map.replace(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            });
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getAbsolutePath());
        }
        return map;
    }

    // Opgave 2C: Udvidelse af P15.1
    public Map<Integer, Set<String>> lengtOfWords(boolean sorted) {
        Map<Integer, Set<String>> mapOfSets = sorted ? new TreeMap<>() : new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.forEachRemaining(word -> {
                final String cleanWord = clean(word);
                mapOfSets.compute(cleanWord.length(), (t, u) -> {
                    Set<String> set = new TreeSet<>();
                    if (u == null) return set;
                    
                    set.addAll(mapOfSets.get(cleanWord.length()));
                    set.add(cleanWord);
                    return set;
                });
            });
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getAbsolutePath());
        }
        return mapOfSets;
    }

    private String clean(String s) {
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                r = r + c;
            }
        }
        return r.toLowerCase();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        TextAnalyzer ta = new TextAnalyzer("alice30.txt");

        // Opgave 2A. Find alle unikke ord i filen
        Set<String> set = ta.findUniqueWords(true);
        System.out.println(set);
        System.out.println("Number of unique words: " + set.size());
        System.out.println("\n------------------------------------------------------------------\n");

        // Opgave 2B. Tæl forekomster af ord
        Map<String, Integer> map = ta.countWords(true);
        System.out.println(map);

        System.out.println("\n------------------------------------------------------------------\n");

        // Opgave 2C. Benyt en mappe til at gruppere ord efter længde
        Map<Integer, Set<String>> map2 = ta.lengtOfWords(true);
        System.out.println(map2);
    }

}
