package vop_02d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Double> doubles = convertToDouble(readTextFile("Doubles.txt"));
        System.out.println("Min: " + getMin(doubles));
        System.out.println("Max: " + getMax(doubles));
        System.out.println("Sum: " + getSum(doubles));
        System.out.println("Average: " + getAverage(doubles));
    }

    public static List<Double> convertToDouble(List<String> data) {
        List<Double> doubleData = new ArrayList<>();

        for (String line : data) {
            try {
                doubleData.add(Double.valueOf(line));
            } catch (NumberFormatException ex) {
                System.err.println(line + " is not a double");
            }
        }
        return doubleData;
    }

    public static List<String> readTextFile(String filename) {
        File file = new File(filename);
        List<String> data = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to load file: " + file.getAbsolutePath());
        }
        return data;
    }

    public static double getMin(List<Double> data) {
        Collections.sort(data, (Double o1, Double o2) -> {
            return (int) (o1 - o2);
        });
        return data.get(0);
    }

    public static double getMax(List<Double> data) {
        Collections.sort(data, (Double o1, Double o2) -> {
            return (int) (o2 - o1);
        });
        return data.get(0);
    }

    public static double getSum(List<Double> data) {
        double sum = 0;
        for (Double number : data) {
            sum += number;
        }
        return sum;
    }
    
    public static double getAverage(List<Double> data) {
        return getSum(data) / data.size();
    }
}
